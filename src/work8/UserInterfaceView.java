package work8;

import java.io.IOException;
import java.util.Scanner;

public class UserInterfaceView {
    private Controller controller = new Controller();

    public void runInterface() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите название города на англ языке: ");
            while (scanner.hasNext("[^a-zA-z]+")) {
                System.out.println("допустим ввод только латинских символов");
                scanner.next();
            }
            String city = scanner.next();

            System.out.println("Введите \"1\" для получения погоды на сегодня;" +
                    "Введите \"5\" для прогноза на 5 дней; Для выхода введите \"0\":");
            while (!scanner.hasNext("[150]")) {
                System.out.println("Другие данные не допустимы!");
                scanner.next();
            }
            String command = scanner.next();

            //TODO* Сделать метод валидации пользовательского ввода

            if (command.equals("0")) break;

            try {
                controller.getWeather(command, city);
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
        }
    }
}
