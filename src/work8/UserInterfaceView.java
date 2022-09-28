package work8;

import java.io.IOException;
import java.util.Scanner;

public class UserInterfaceView {
    private Controller controller = new Controller();

    public void runInterface() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("������� �������� ������ �� ���� �����: ");
            while (scanner.hasNext("[^a-zA-z]+")) {
                System.out.println("�������� ���� ������ ��������� ��������");
                scanner.next();
            }
            String city = scanner.next();

            System.out.println("������� \"1\" ��� ��������� ������ �� �������;" +
                    "������� \"5\" ��� �������� �� 5 ����; ��� ������ ������� \"0\":");
            while (!scanner.hasNext("[150]")) {
                System.out.println("������ ������ �� ���������!");
                scanner.next();
            }
            String command = scanner.next();

            //TODO* ������� ����� ��������� ����������������� �����

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
