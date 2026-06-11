package kreslavskiy.weathercams;

import com.andrewoid.apikeys.ApiKey;
import io.reactivex.rxjava3.annotations.NonNull;
import org.junit.jupiter.api.Test;
import retrofit2.http.Query;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OpenweathermapServiceTest
{
    @Test
    void getLocation()
    {
        //given
        OpenweathermapService owmService = new OpenweathermapServiceFactory().create();

        //when
        ApiKey apiKey = new ApiKey("openweathermapKey");
        String keyString = apiKey.get();

        List<Location> locations = owmService.getLocation(
                         "Passaic", 5, keyString).blockingGet();

        //then
        assertEquals("40.8566936", locations.get(0).lat());
        assertEquals("-74.1286298", locations.get(0).lon());
    }

    @Test
    void getWeather()
    {
        //given
        OpenweathermapService owmService = new OpenweathermapServiceFactory().create();

        //when
        ApiKey apiKey = new ApiKey("openweathermapKey");
        String keyString = apiKey.get();

        WeatherInfo weatherInfo = owmService.getWeather(40.8566936, -74.1286298,
                                                            "imperial", keyString).blockingGet();

        //then
        assertNotNull(weatherInfo);
        assertInstanceOf(String.class, weatherInfo.weather().get(0).description());
        assertInstanceOf(Double.class, weatherInfo.main().temp());
        assertInstanceOf(Double.class, weatherInfo.main().feelsLike());
    }
}