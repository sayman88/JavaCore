package work7;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class AccuwhetherModel implements WeatherModel {
    private static final String PROTOKOL = "http";
    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String FORECASTS = "forecasts";
    private static final String VERSION = "v1";
    private static final String DAILY = "daily";
    private static final String ONE_DAY = "1day";
    private static final String FIVE_DAY = "5day";
    //    private static final String API_KEY = "0CkzH070JCn859NBOObFBcCPueH1cmAK";
//    private static final String API_KEY = "diboclPwuPqpbFizDohtFpTQ1flG3HLz";
    private static final String API_KEY = "MuLZF172nqwG6Wn6EL6Gdn0GWmXG9Idg";
    private static final String API_KEY_QUERY_PARAM = "apikey";
    private static final String LOCATIONS = "locations";
    private static final String CITIES = "cities";
    private static final String AUTOCOMPLETE = "autocomplete";
    private static final String QUEST = "q";
    private static final String METRIC = "true";
    private static final String METRIC_PARAM = "metric";
    private static final String LANG = "ru";
    private static final String LANG_PARAM = "language";

    private static final OkHttpClient okHttpClient = new OkHttpClient();
    static final ObjectMapper objectMapper = new ObjectMapper();//уточнить private
    private static final ResponseParse responseParse = new ResponseParse();

    public void getWeather(String selectedCity, Period period) throws IOException {
        switch (period) {
            case NOW:
                HttpUrl httpUrl = new HttpUrl.Builder()
                        .scheme(PROTOKOL)
                        .host(BASE_HOST)
                        .addPathSegment(FORECASTS)
                        .addPathSegment(VERSION)
                        .addPathSegment(DAILY)
                        .addPathSegment(ONE_DAY)
                        .addPathSegment(getCityKey(selectedCity)[0])
                        .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                        .addQueryParameter(METRIC_PARAM, METRIC)
                        .addQueryParameter(LANG_PARAM, LANG)
                        .build();

                Request request = new Request.Builder()
                        .url(httpUrl)
                        .build();

                Response oneDayForecastResponse = okHttpClient.newCall(request).execute();
                String weatherOneDayResponse = oneDayForecastResponse.body().string();

                //TODO: сделать человекочитаемый вывод погоды. Выбрать параметры для вывода на свое усмотрение
                //Например: Погода в городе Москва - 5 градусов по цельсию Expect showers late Monday night
                System.out.println("Город - " + getCityKey(selectedCity)[1]);
                responseParse.parseWeatherResponse(weatherOneDayResponse);
                break;
            case FIVE_DAYS:
                //TODO*: реализовать вывод погоды на 5 дней
                HttpUrl httpUrl1 = new HttpUrl.Builder()
                        .scheme(PROTOKOL)
                        .host(BASE_HOST)
                        .addPathSegment(FORECASTS)
                        .addPathSegment(VERSION)
                        .addPathSegment(DAILY)
                        .addPathSegment(FIVE_DAY)
                        .addPathSegment(getCityKey(selectedCity)[0])
                        .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                        .addQueryParameter(METRIC_PARAM, METRIC)
                        .addQueryParameter(LANG_PARAM, LANG)
                        .build();

                Request request1 = new Request.Builder()
                        .url(httpUrl1)
                        .build();

                Response fiveDayForecastResponse = okHttpClient.newCall(request1).execute();
                String weatherFiveDayResponse = fiveDayForecastResponse.body().string();
                System.out.println("Город - " + getCityKey(selectedCity)[1]);
                responseParse.parseWeatherFiveDay(weatherFiveDayResponse, period);
                break;
            default:
                throw new IllegalArgumentException("Прогноз погоды может быть только на 1день или на 5дней. " +
                        "ВВедите корректное значение!");
        }
    }

    public String[] getCityKey(String selectedCity) throws IOException {
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme(PROTOKOL)
                .host(BASE_HOST)
                .addPathSegment(LOCATIONS)
                .addPathSegment(VERSION)
                .addPathSegment(CITIES)
                .addPathSegment(AUTOCOMPLETE)
                .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                .addQueryParameter(QUEST, selectedCity)
                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .get()
                .build();

        Response response = okHttpClient.newCall(request).execute();
        String responseString = response.body().string();
        String cityKey = objectMapper.readTree(responseString).get(0).at("/Key").asText();
        String cityName = objectMapper.readTree(responseString).get(0).at("/LocalizedName").asText();
        String[] cityDataArray = {cityKey, cityName};
        return cityDataArray;
    }
}