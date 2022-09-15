package work4;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {


        //Task 1
        String[] testWords = {"Peter", "Mark", "John", "Bob", "Michael", "John", "Peter", "Rob", "John", "Patrick", "Ryan"};
        HashMap<String, Integer> words = new HashMap<>();

        System.out.println("-----------------------");
        System.out.println("Task #1");
        System.out.println("-----------------------");

        //All words
        System.out.println("All words: ");

        for (String word: testWords) {
            if (!words.containsKey(word)) {
                words.put(word, 1);
            } else {
                words.put(word, words.get(word) + 1);
            }
            System.out.print(word + " ");
        }
        System.out.println();

        //Unique words
        System.out.println("Unique words: ");
        System.out.println(words);


        //Task #2
        System.out.println("-----------------------");
        System.out.println("Task #2");
        System.out.println("-----------------------");

        PhoneBook book = new PhoneBook();
        book.add("Ivanov", "12345678");
        //name duplicate will add a phone for Ivanov
        book.add("Ivanov", "12345679");
        book.add("Ivanov", "12345670");
        book.add("Petrov", "34567013");
        book.add("Petrov", "34567014");
        //full duplicate will be ignored
        book.add("Petrov", "34567014");
        book.add("Sidorov", "56701434");

        book.print();

        //Test get() nor real name
        System.out.println("Phones for Petrov");
        System.out.println(book.get("Petrov"));

        //Test name nor found case
        System.out.println("Phones for Kuznetsov");
        System.out.println(book.get("Kuznetsov"));
    }
}
