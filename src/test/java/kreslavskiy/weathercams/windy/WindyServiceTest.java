package kreslavskiy.weathercams.windy;

import com.andrewoid.apikeys.ApiKey;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WindyServiceTest
{
    @Test
    void getWindy()
    {
        //given
        WindyService windyService = new WindyServiceFactory().create();

        //when
        ApiKey apiKey = new ApiKey("windyKey");
        String keyString = apiKey.get();

        WebcamInfo webcamInfo = windyService.getWindy(keyString, "40.8566936,-74.1286298,10").blockingGet();

        //then
        assertNotNull(webcamInfo);
        assertFalse(webcamInfo.webcams().isEmpty());
    }
}