package kreslavskiy.weathercams;

import com.andrewoid.apikeys.ApiKey;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class LocationController
{
    private final OpenweathermapService owmService;
    private final JTextField location;
    private final JLabel latitude;
    private final JLabel longitude;
    private final JLabel temperature;
    private final JLabel feelsLike;
    private final JLabel description;

    public LocationController(OpenweathermapService owmService, JTextField location, JLabel latitude, JLabel longitude,
                                JLabel temperature, JLabel feelsLike, JLabel description)
    {
        this.owmService = owmService;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.description = description;
    }

    public void doSearch()
    {
        String locationName = location.getText();
        ApiKey apiKey = new ApiKey("openweathermapKey");
        String keyString = apiKey.get();

        Disposable disposable = owmService.getLocation(locationName, 5, keyString)
                // tells Rx to request the data on a background Thread
                .subscribeOn(Schedulers.io())
                // tells Rx to handle the response on Swing's main Thread
                .observeOn(Schedulers.from(SwingUtilities:: invokeLater))
                .subscribe(
                        this:: handleResponse,
                        Throwable:: printStackTrace);
    }

    private void handleResponse(List<Location> locationsArr)
    {
        if (!locationsArr.isEmpty())
        {
            Location loc1 = locationsArr.get(0);
            latitude.setText(String.valueOf(loc1.lat()));
            longitude.setText(String.valueOf(loc1.lon()));

            ApiKey apiKey = new ApiKey("openweathermapKey");
            String keyString = apiKey.get();
            String units = "imperial";

            owmService.getWeather(Double.parseDouble(loc1.lat()), Double.parseDouble(loc1.lon()), units, keyString)
                    // tells Rx to request the data on a background Thread
                    .subscribeOn(Schedulers.io())
                    // tells Rx to handle the response on Swing's main Thread
                    .observeOn(Schedulers.from(SwingUtilities:: invokeLater))
                    .subscribe(
                            this:: handleWeatherResponse,
                            Throwable:: printStackTrace);
        }
    }

    private void handleWeatherResponse(WeatherInfo weatherInfo)
    {
        temperature.setText(String.valueOf(weatherInfo.main().temp()));
        feelsLike.setText(String.valueOf(weatherInfo.main().feelsLike()));
        description.setText(weatherInfo.weather().get(0).description());
    }

}
