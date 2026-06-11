package kreslavskiy.weathercams.windy;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Header;
import retrofit2.http.Query;
import retrofit2.http.GET;

import java.util.List;

public interface WindyService
{
    @GET("/webcams/api/v3/webcams?limit=5&include=categories,images,location")
    Single<WebcamInfo> getWindy(
            @Header("x-windy-api-key") String apiKey,
            @Query("nearby") String latLon);
}
