package work6;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class AccuweatherApi {
    public static void main(String[] args) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();

        String apikey = "0CkzH070JCn859NBOObFBcCPueH1cmAK";
        String city = "yekaterinburg";

        HttpUrl httpUrlGetCityKey = new HttpUrl.Builder()
                .scheme("http")
                .host("dataservice.accuweather.com")
                .addPathSegment("locations")
                .addPathSegment("v1")
                .addPathSegment("cities")
                .addPathSegment("autocomplete")
                .addQueryParameter("apikey", apikey)
                .addQueryParameter("q", city)
                .build();

        Request requestGetCityKey = new Request.Builder()
                .url(httpUrlGetCityKey)
                .get()
                .build();

        Response checkAuthKeyResponse = okHttpClient.newCall(requestGetCityKey).execute();
        System.out.println(checkAuthKeyResponse.code());
        System.out.println(checkAuthKeyResponse.headers());

        String bodyCityKey = checkAuthKeyResponse.body().string();
        System.out.println(bodyCityKey);

        String cityKey = bodyCityKey.split(",")[1];
        cityKey = cityKey.replaceAll("\\D", "");
        System.out.println(cityKey);


        HttpUrl httpUrlGetWeatherFiveDay = new HttpUrl.Builder()
                .scheme("http")
                .host("dataservice.accuweather.com")
                .addPathSegment("forecasts")
                .addPathSegment("v1")
                .addPathSegment("daily")
                .addPathSegment("5day")
                .addPathSegment(cityKey)
                .addQueryParameter("apikey", apikey)
                .addQueryParameter("language","ru")
//                .addQueryParameter("details","false")
                .addQueryParameter("metric","true")
                .build();

        Request requestWeatherFiveDay = new Request.Builder()
                .url(httpUrlGetWeatherFiveDay)
                .get()
                .build();

        Response weatherFiveDay = okHttpClient.newCall(requestWeatherFiveDay).execute();
        System.out.println(weatherFiveDay.code());
//        System.out.println(weatherFiveDay.headers());

        String bodyWeatherFiveDay = weatherFiveDay.body().string();
        System.out.println(bodyWeatherFiveDay);

    }
}