package work7;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Controller {
    private final WeatherModel weatherModel = new AccuwhetherModel();
    private final Map<Integer, Period> variants = new HashMap<>();

    public Controller() {
        variants.put(1, Period.NOW);
        variants.put(5, Period.FIVE_DAYS);
    }

    public void getWeather(String userInput, String selectedCity) throws IOException {
        Integer userIntegerInput = Integer.parseInt(userInput);

        switch (variants.get(userIntegerInput)) {
            case NOW -> weatherModel.getWeather(selectedCity, Period.NOW);
            case FIVE_DAYS -> weatherModel.getWeather(selectedCity, Period.FIVE_DAYS);
            default -> throw new IllegalArgumentException("Выберете период: 1день или 5дней!");
        }
    }
}