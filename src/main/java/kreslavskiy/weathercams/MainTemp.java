package kreslavskiy.weathercams;

import com.google.gson.annotations.SerializedName;

public record MainTemp(double temp, @SerializedName("feels_like") double feelsLike)
{
}

//Used @SerializedName to rename 'feel_like' and remove the underbar
// to avoid the GitHub checkstyle error from the underbar in the name 'feels_like'