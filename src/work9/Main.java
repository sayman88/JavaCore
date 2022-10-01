package work9;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {


        List<Student> listOfStudentsAndCourses = new ArrayList<>();
        listOfStudentsAndCourses.add(new Student("Ivan", Arrays.asList(new Course("course1"),
                new Course("course2"), new Course("course3"))));
        listOfStudentsAndCourses.add(new Student("Petr", Arrays.asList(new Course("course2"),
                new Course("course3"))));
        listOfStudentsAndCourses.add(new Student("Vasiliy", Arrays.asList(new Course("course1"))));
        listOfStudentsAndCourses.add(new Student("Oksana", Arrays.asList(new Course("course1"),
                new Course("course2"), new Course("course3"), new Course("course4"))));
        listOfStudentsAndCourses.add(new Student("Elena", Arrays.asList(new Course("course1"),
                new Course("course2"))));
        listOfStudentsAndCourses.add(new Student("Irina", Arrays.asList(new Course("course1"),
                new Course("course2"), new Course("course3"), new Course("course4"), new Course("course5"))));

        listOfStudentsAndCourses.stream().forEach(System.out::println);
        System.out.println("1.___________________");

        /*Написать функцию, принимающую список Student и возвращающую список уникальных курсов, на которые подписаны студенты.*/
        listOfStudentsAndCourses.stream()
                .map(s -> s.getCourses())
                .flatMap(f -> f.stream())
                .distinct()
                .forEach(System.out::println);

        /*Написать функцию, принимающую на вход список Student и возвращающую список из
        трех самых любознательных (любознательность определяется количеством курсов).*/

        System.out.println("2.___________________");
        listOfStudentsAndCourses.stream()
                .sorted((o1, o2) -> o2.getCourses().size() - o1.getCourses().size())
                .limit(3)
                .map(s -> s.getName())
                .collect(Collectors.toList())
                .forEach(System.out::println);

        /*Написать функцию, принимающую на вход список Student и экземпляр Course, возвращающую список студентов,
        которые посещают этот курс.*/
        System.out.println("3.___________________");
        System.out.println(doSomething(listOfStudentsAndCourses, new Course("course4")));
    }

    public static List<String> doSomething(List<Student> listOfStudentsAndCourses, Course course) {
        return listOfStudentsAndCourses.stream()
                .filter(x -> x.getCourses().contains(course))
                .map(Student::getName)
                .collect(Collectors.toList());
    }
}