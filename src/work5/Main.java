package work5;

import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        File file = new File("src/work5/test.csv");
        try {
            System.out.println("Создаем новый файл - " + file);
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("указан не верный путь файла...");
        }

        String[] headerArray = {"header1", "header2", "header3"};

        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter("src/work5/test.csv"))) {
            bufferedWriter.write(Arrays.toString(headerArray));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int[][] valueArray = {{100, 200, 123}, {300, 400, 500}};
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter("src/work5/test.csv", true))) {
            for (int i = 0; i < valueArray.length; i++) {
                bufferedWriter.write("\n");
                for (int j = 0; j < valueArray[i].length; j++) {
                    bufferedWriter.write(valueArray[i][j] + ";");
                }
            }
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader("src/work5/test.csv"))) {
            System.out.println(bufferedReader.readLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

