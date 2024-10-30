package mehdi.sample.service;

import mehdi.sample.DTO.ClickEventDTO;
import mehdi.sample.DTO.ImpressionEventDTO;
import mehdi.sample.DTO.MetricDTO;
import mehdi.sample.exception.ResourceNotFoundException;
import mehdi.sample.repository.MetricsRepository;
import mehdi.sample.utility.JsonFilesUtils;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.expressions.Window;
import org.apache.spark.sql.expressions.WindowSpec;
import org.apache.spark.sql.functions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetricService {

    private MetricsRepository metricsRepository;

    private SparkSession spark;

    private JsonFilesUtils jsonFilesUtils;

    @Autowired
    public MetricService(MetricsRepository metricsRepository,SparkSession spark,JsonFilesUtils jsonFilesUtils) {
        this.metricsRepository = metricsRepository;
        this.spark = spark;
        this.jsonFilesUtils = jsonFilesUtils;
    }

    public List<MetricDTO> getMetricsFromAppAndCountry() {
        List<MetricDTO> metricDTOS = metricsRepository.calculateMetricsFromAppAndCountry();
        if (metricDTOS.isEmpty()) {
            throw new ResourceNotFoundException("No metrics found","ERR-20");
        }
        return metricDTOS;
    }
    public String getTopAdvertisersFromAppAndCountry() {

        // Create DataFrames from the lists
        Dataset<Row> impressionsDS = spark.createDataFrame(jsonFilesUtils.readImpressionEvents(), ImpressionEventDTO.class);
        Dataset<Row> clicksDS = spark.createDataFrame(jsonFilesUtils.readClickEvents(), ClickEventDTO.class);

        // Register the DataFrames as temporary views to use SQL queries
        impressionsDS.createOrReplaceTempView("impressions");
        clicksDS.createOrReplaceTempView("clicks");

        // Calculate revenue per impression
        Dataset<Row> resultDF = spark.sql(
                "SELECT " +
                        "i.appId, " +
                        "i.countryCode, " +
                        "i.advertiserId, " +
                        "SUM(c.revenue) AS total_revenue, " +
                        "COUNT(i.impressionId) AS total_impressions, " +
                        "SUM(c.revenue) / COUNT(i.impressionId) AS revenue_per_impression " +
                        "FROM impressions i " +
                        "JOIN clicks c ON i.impressionId = c.impressionId " +
                        "WHERE  (i.appId IS NOT NULL AND TRIM(i.appId) != '') and (i.countryCode IS NOT NULL AND TRIM(i.countryCode) != '')" +
                        "GROUP BY i.appId, i.countryCode, i.advertiserId"
        );

        // Find top 5 advertisers by revenue per impression
        WindowSpec windowSpec = Window.partitionBy("appId", "countryCode")
                .orderBy(functions.desc("revenue_per_impression"));

        Dataset<Row> topAdvertisersDF = resultDF
                .withColumn("max_advertiser", functions.row_number().over(windowSpec))
                .filter("max_advertiser <= 5")
                .groupBy("appId", "countryCode")
                .agg(functions.collect_list("advertiserId").alias("recommended_advertiser_ids"));

        // Show the result
        topAdvertisersDF.show(false);

        // Write metrics to a JSON file
        return topAdvertisersDF.toJSON().collectAsList().toString();
    }
}
