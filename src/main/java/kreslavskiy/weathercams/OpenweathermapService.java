package kreslavskiy.weathercams;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface OpenweathermapService
{
    @GET("geo/1.0/direct")
    Single<List<Location>> getLocation(
            @Query("q") String locationName,
            @Query("limit") int limit,
            @Query("appid")  String apiKey);

}
