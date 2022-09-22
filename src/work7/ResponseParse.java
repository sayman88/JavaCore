package work7;

import java.io.IOException;
import java.util.Arrays;

import static work7.AccuwhetherModel.objectMapper;

public class ResponseParse {
    public void parseWeatherResponse(String weatherResponse) throws IOException {
        String data = objectMapper.readTree(weatherResponse).at("/Headline").at("/EffectiveDate").asText().
                split("T")[0];
        String minTemp = objectMapper.readTree(weatherResponse).at("/DailyForecasts").get(0).
                at("/Temperature/Minimum/Value").asText();
        String unitMinTemp = objectMapper.readTree(weatherResponse).at("/DailyForecasts").get(0).
                at("/Temperature/Minimum/Unit").asText();
        String maxTemp = objectMapper.readTree(weatherResponse).at("/DailyForecasts").get(0).
                at("/Temperature/Maximum/Value").asText();
        String unitMaxTemp = objectMapper.readTree(weatherResponse).at("/DailyForecasts").get(0).
                at("/Temperature/Maximum/Unit").asText();
        String dayDesc = objectMapper.readTree(weatherResponse).at("/DailyForecasts").get(0).
                at("/Day/IconPhrase").asText();

        String prec = objectMapper.readTree(weatherResponse).at("/DailyForecasts").get(0).
                at("/Day/HasPrecipitation").asText();
        String a;
        a = "ожидаются";

        String nightDesc = objectMapper.readTree(weatherResponse).at("/DailyForecasts").get(0).
                at("/Night/IconPhrase").asText();
        String b;
        if (prec.equals(false)) {
            b = "не ожидаются";
        } else {
            b = "ожидаются";
        }
        String nightDescPrecType = objectMapper.readTree(weatherResponse).at("/DailyForecasts").get(0).
                at("/Night/PrecipitationType").asText();
        String nightDescPrecIntensity = objectMapper.readTree(weatherResponse).at("/DailyForecasts").get(0).
                at("/Night/PrecipitationIntensity").asText();

        String weatherForecastOneDay = "Дата: " + data
                + "\nМинимальная температура воздуха: " + minTemp + unitMinTemp
                + "\nМаксимальная температура воздуха: " + maxTemp + unitMaxTemp
                + "\nДнём: " + dayDesc + ", осадки - " + a
                + "\nНочью: " + nightDesc + ", осадки - " + b + " " + nightDescPrecType + " "
                + nightDescPrecIntensity;

        System.out.println(weatherForecastOneDay);
    }

    public void parseWeatherFiveDay(String responseBodyFiveDayWeather, Period command) throws IOException {
        String[] weatherForecastFiveDay = new String[5];
        for (int i = 0; i < 5; i++) {
            String data2 = objectMapper.readTree(responseBodyFiveDayWeather).at("/DailyForecasts").get(i).at("/Date").asText().
                    split("T")[0];
            String minTemp = objectMapper.readTree(responseBodyFiveDayWeather).at("/DailyForecasts").get(i).
                    at("/Temperature/Minimum/Value").asText();
            String unitMinTemp = objectMapper.readTree(responseBodyFiveDayWeather).at("/DailyForecasts").get(i).
                    at("/Temperature/Minimum/Unit").asText();
            String maxTemp = objectMapper.readTree(responseBodyFiveDayWeather).at("/DailyForecasts").get(i).
                    at("/Temperature/Maximum/Value").asText();
            String unitMaxTemp = objectMapper.readTree(responseBodyFiveDayWeather).at("/DailyForecasts").get(i).
                    at("/Temperature/Maximum/Unit").asText();
            String dayDesc = objectMapper.readTree(responseBodyFiveDayWeather).at("/DailyForecasts").get(i).
                    at("/Day/IconPhrase").asText();

            String prec = objectMapper.readTree(responseBodyFiveDayWeather).at("/DailyForecasts").get(i).
                    at("/Day/HasPrecipitation").asText();
            String a;
            if (prec.equals(false)) {
                a = "не ожидаются";
            } else {
                a = "ожидаются";
            }

            String nightDesc = objectMapper.readTree(responseBodyFiveDayWeather).at("/DailyForecasts").get(i).
                    at("/Night/IconPhrase").asText();
            String b;
            if (prec.equals(false)) {
                b = "не ожидаются";
            } else {
                b = "ожидаются";
            }
            String nightDescPrecType = objectMapper.readTree(responseBodyFiveDayWeather).at("/DailyForecasts").get(i).
                    at("/Night/PrecipitationType").asText();
            String nightDescPrecIntensity = objectMapper.readTree(responseBodyFiveDayWeather).at("/DailyForecasts").get(i).
                    at("/Night/PrecipitationIntensity").asText();

            weatherForecastFiveDay[i] = "Дата: " + data2
                    + "\nМинимальная температура воздуха: " + minTemp + unitMinTemp
                    + "\nМаксимальная температура воздуха: " + maxTemp + unitMaxTemp
                    + "\nДнём: " + dayDesc + ", осадки - " + a
                    + "\nНочью: " + nightDesc + ", осадки - " + b + " " + nightDescPrecType + " "
                    + nightDescPrecIntensity + "\n\n";
        }
        System.out.println(Arrays.deepToString(weatherForecastFiveDay));
    }
}