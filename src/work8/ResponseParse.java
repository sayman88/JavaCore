package work8;

import java.io.IOException;

import static work7.AccuwhetherModel.objectMapper;

public class ResponseParse {
    public String[] parseWeatherResponse(String weatherResponse) throws IOException {
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
        if (prec.equals(false)) {
            a = "�� ���������";
        } else {
            a = "���������";
        }

        String nightDesc = objectMapper.readTree(weatherResponse).at("/DailyForecasts").get(0).
                at("/Night/IconPhrase").asText();
        String b;
        if (prec.equals(false)) {
            b = "�� ���������";
        } else {
            b = "���������";
        }
        String nightDescPrecType = objectMapper.readTree(weatherResponse).at("/DailyForecasts").get(0).
                at("/Night/PrecipitationType").asText();
        String nightDescPrecIntensity = objectMapper.readTree(weatherResponse).at("/DailyForecasts").get(0).
                at("/Night/PrecipitationIntensity").asText();

        String[] weatherForecastOneDayArray = {
                data,
                (minTemp + unitMinTemp),
                (maxTemp + unitMaxTemp),
                dayDesc,
                a,
                nightDesc,
                b,
                (nightDescPrecType + " " + nightDescPrecIntensity)};

        return weatherForecastOneDayArray;
    }

    public String[][] parseWeatherFiveDay(String responseBodyFiveDayWeather) throws IOException {
        String[][] weatherForecastFiveDay = new String[5][8];
        try {
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
                    a = "�� ���������";
                } else {
                    a = "���������";
                }

                String nightDesc = objectMapper.readTree(responseBodyFiveDayWeather).at("/DailyForecasts").get(i).
                        at("/Night/IconPhrase").asText();
                String b;
                if (prec.equals(false)) {
                    b = "�� ���������";
                } else {
                    b = "���������";
                }

                String nightDescPrecType = objectMapper.readTree(responseBodyFiveDayWeather).at("/DailyForecasts").get(i).
                        at("/Night/PrecipitationType").asText();
                String nightDescPrecIntensity = objectMapper.readTree(responseBodyFiveDayWeather).at("/DailyForecasts").get(i).
                        at("/Night/PrecipitationIntensity").asText();
//            weatherForecastFiveDay[i] = "����: " + data2
//                    + "\n����������� ����������� �������: " + minTemp + unitMinTemp
//                    + "\n������������ ����������� �������: " + maxTemp + unitMaxTemp
//                    + "\n���: " + dayDesc + ", ������ - " + a
//                    + "\n�����: " + nightDesc + ", ������ - " + b + " " + nightDescPrecType + " "
//                    + nightDescPrecIntensity + "\n\n";

                for (int j = 0; j < 8; j++) {
                    String[] tmpArr = {data2 + "," + (minTemp + unitMinTemp) + "," + (maxTemp + unitMaxTemp) + "," + dayDesc + "," + a + "," +
                            nightDesc + "," +
                            b + "," +
                            (nightDescPrecType + " " + nightDescPrecIntensity)};
                    weatherForecastFiveDay[i] = tmpArr;
                }
//                System.out.println(Arrays.toString(weatherForecastFiveDay));
            }
        } catch (NullPointerException e) {
            System.out.println("��� ������ � ������!");
            e.printStackTrace();
        }
        return weatherForecastFiveDay;
    }
}
