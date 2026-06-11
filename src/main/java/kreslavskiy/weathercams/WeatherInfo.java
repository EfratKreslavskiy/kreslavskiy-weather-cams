package kreslavskiy.weathercams;

import java.util.List;

public record WeatherInfo(List<Weather> weather, MainTemp main)
{
}
