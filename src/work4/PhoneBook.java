package work4;

import java.util.HashMap;
import java.util.HashSet;

public class PhoneBook {
    HashMap<String, HashSet<String>> book;

    public PhoneBook() {
        this.book = new HashMap<String, HashSet<String>>();
    }

    public void add(String name, String phone) {
        if (!book.containsKey(name)) {
            HashSet<String> newEntry = new HashSet<>();
            newEntry.add(phone);
            book.put(name, newEntry);
        } else {
            HashSet<String> phones = book.get(name);
            phones.add(phone);
        }
    }

    public String get(String name) {
        String result;

        if (book.containsKey(name)) {
            HashSet<String> phones = book.get(name);
            result = phones.toString();
        } else {
            result = "Name " + name + " was not found!";
        }
        return result;
    }

    public void print() {
        for (String name : book.keySet()) {
            System.out.print("Name: " + name + ". Phones: ");
            System.out.println(get(name));
        }
    }
}
