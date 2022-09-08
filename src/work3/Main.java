package work3;

public class Main {
    public static void main(String[] args) {
        System.out.println("work 3. Task 1");

        Integer[] arrayInteger = {1, 2, 3, 4, 5};
        String[] arrayString = {"A", "B", "C", "D", "E"};

        AbstractArray<Integer> testArrayInt = new AbstractArray<>(arrayInteger);
        AbstractArray<String> testArrayStr = new AbstractArray<>(arrayString);

        AbstractArray.testArraySwap(testArrayInt, 2, 3);
        AbstractArray.testArraySwap(testArrayStr, 3, 4);

        System.out.println("work 3. Task 2");

        Box<Orange> orangeBox = new Box<>(new Orange(), new Orange(), new Orange(), new Orange());
        Box<Apple> appleBox1 = new Box<>(new Apple(), new Apple(), new Apple());
        Box<Apple> appleBox2 = new Box<>(new Apple(), new Apple(), new Apple(), new Apple(), new Apple(), new Apple());

        orangeBox.printBox();
        appleBox1.printBox();
        appleBox2.printBox();

        System.out.println("Compare orangeBox and appleBox1 :" + orangeBox.compareTo(appleBox1));
        System.out.println("Compare orangeBox and appleBox2 :" + orangeBox.compareTo(appleBox2));

        System.out.println("Moving orangeBox to appleBox1 -  - check fruits via exceptions");
        try {
            orangeBox.moveToAnyBox(appleBox1);
        } catch (InvalidBoxFruitTypeException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Moving appleBox1 to appleBox2 - fruit type checked by Java");
        try {
            appleBox1.moveToSameTypeBox(appleBox2);
        } catch (InvalidBoxFruitTypeException e) {
            System.out.println(e.getMessage());
        }
        appleBox1.printBox();
        appleBox2.printBox();

    }
}
