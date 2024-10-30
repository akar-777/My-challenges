package mehdi.sample.service;

import mehdi.sample.DTO.ClickEventDTO;
import mehdi.sample.DTO.ImpressionEventDTO;
import mehdi.sample.utility.JsonFilesUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class MetricServiceTest {

    @Autowired
    private MetricService metricService;

    @MockBean
    private JsonFilesUtils jsonFilesUtils;

    @Test
    void getTopAdvertisersFromAppAndCountryTest() {

        // define mock data for impression event
        List<ImpressionEventDTO> mockImpressions = Arrays.asList(
                new ImpressionEventDTO("impression1", 1, "US", 100),
                new ImpressionEventDTO("impression2", 1, "US", 101),
                new ImpressionEventDTO("impression3", 1, "US", 100),
                new ImpressionEventDTO("impression4", 2, "CA", 102),
                new ImpressionEventDTO("impression5", 2, "IT", 101),
                new ImpressionEventDTO("impression6", 2, "IT", 101),
                new ImpressionEventDTO("impression7", 2, "IT", 101),
                new ImpressionEventDTO("impression8", 2, "CA", 101)
        );

        // define mock data for click event
        List<ClickEventDTO> mockClicks = Arrays.asList(
                new ClickEventDTO("impression1", 5.0),
                new ClickEventDTO("impression2", 3.0),
                new ClickEventDTO("impression3", 2.0),
                new ClickEventDTO("impression4", 10.0),
                new ClickEventDTO("impression5", 10.0),
                new ClickEventDTO("impression6", 10.0),
                new ClickEventDTO("impression7", 10.0),
                new ClickEventDTO("impression8", 10.0)
        );

        // Mock the responses of jsonFilesUtils methods
        when(jsonFilesUtils.readImpressionEvents()).thenReturn(mockImpressions);
        when(jsonFilesUtils.readClickEvents()).thenReturn(mockClicks);

        // Call the method under test
        String result = metricService.getTopAdvertisersFromAppAndCountry();

        // Expected JSON output
        String expectedOutput = "[{\"appId\":1,\"countryCode\":\"US\",\"recommended_advertiser_ids\":[100,101]}, {\"appId\":2,\"countryCode\":\"CA\",\"recommended_advertiser_ids\":[102,101]}, {\"appId\":2,\"countryCode\":\"IT\",\"recommended_advertiser_ids\":[101]}]";

        // Assertion results to match business result and expectation
        assertEquals(expectedOutput, result);
    }
}
