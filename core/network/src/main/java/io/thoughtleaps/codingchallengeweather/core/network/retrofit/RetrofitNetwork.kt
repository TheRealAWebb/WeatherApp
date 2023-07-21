package io.thoughtleaps.codingchallengeweather.core.network.retrofit

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import io.thoughtleaps.codingchallengeweather.core.network.BuildConfig
import io.thoughtleaps.codingchallengeweather.core.network.model.GeoDirectResponseModel
import io.thoughtleaps.codingchallengeweather.core.network.model.OpenWeatherResponseModel
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

private interface WeatherApi {

    @GET(value = "/data/2.5/weather")
    suspend fun getWeatherByCity(
        @Query("q") cityName: String,
        @Query("units") units: String,
        @Query("appid") apiKey: String,
    ): OpenWeatherResponseModel

    @GET(value = "/data/2.5/weather")
    suspend fun getWeatherByLocation(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String,
        @Query("appid") apiKey: String,
    ): OpenWeatherResponseModel


    @GET(value = "/geo/1.0/direct")
    suspend fun getDirectGeocoding(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String,
        @Query("limit") limit: Int = 5
    ): List<GeoDirectResponseModel>


    @GET(value = "/geo/1.0/reverse")
    suspend fun getReverseGeocoding(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String,
        @Query("limit") limit: Int = 1
    ): List<GeoDirectResponseModel>
}

@Singleton
class RetrofitNetwork @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: Call.Factory,
) : NetworkDataSource {
    private val networkApi = Retrofit.Builder()
        .baseUrl(BaseUrl)
        .callFactory(okhttpCallFactory)
        .addConverterFactory(
            networkJson.asConverterFactory("application/json".toMediaType()),
        )
        .build()
        .create(WeatherApi::class.java)


    override suspend fun getCurrentWeather(
        cityName: String,
    ): OpenWeatherResponseModel = networkApi.getWeatherByCity(cityName, "imperial", ApiKey)

    override suspend fun getCurrentWeatherByLocation(
        latitude: Double,
        longitude: Double,
    ): OpenWeatherResponseModel =
        networkApi.getWeatherByLocation(latitude, longitude, "imperial", ApiKey)


    override suspend fun getDirectGeocoding(cityName: String): List<GeoDirectResponseModel> =
        networkApi.getDirectGeocoding(cityName, ApiKey)

    override suspend fun getReverseGeocoding(
        latitude: Double, longitude: Double
    ): List<GeoDirectResponseModel> = networkApi.getReverseGeocoding(latitude, longitude, ApiKey)

}

private const val BaseUrl = BuildConfig.BACKEND_URL
private const val ApiKey = BuildConfig.API_KEY
