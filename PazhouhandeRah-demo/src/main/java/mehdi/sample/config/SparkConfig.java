package mehdi.sample.config;

import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfig {
    @Bean
    public SparkSession sparkSession() {
        return SparkSession.builder()
                .appName("Spark Session With 8 cores")
                .master("local[8]") // Use all 8 cores
                .getOrCreate();
    }
}
