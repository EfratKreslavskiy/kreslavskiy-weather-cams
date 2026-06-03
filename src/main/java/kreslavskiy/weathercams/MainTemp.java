package kreslavskiy.weathercams;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MainTemp(double temp, @JsonProperty("feels_like") String feelsLike)
{
}

//Used @JsonProperty from FasterXML to rename 'feel_like' and remove the underbar
// to avoid the GitHub checkstyle error from the underbar in the name 'feels_like'