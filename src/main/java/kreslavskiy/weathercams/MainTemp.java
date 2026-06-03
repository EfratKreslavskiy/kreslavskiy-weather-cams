package kreslavskiy.weathercams;

import com.google.gson.annotations.SerializedName;

public record MainTemp(double temp, @SerializedName("feels_like") String feelsLike)
{
}

//Used @JsonProperty from FasterXML to rename 'feel_like' and remove the underbar
// to avoid the GitHub checkstyle error from the underbar in the name 'feels_like'