package work3;

import java.util.ArrayList;
import java.util.Arrays;

public class Box<T extends Fruit>{
    private ArrayList<T> fruits;
    private String fruitName;

    public Box(T... fruits) {
        this.fruits = new ArrayList<>();
        this.fruits.addAll(Arrays.asList(fruits));

        this.fruitName = this.fruits.get(0).getName();
    }

    public ArrayList<T> getFruits() {
        return fruits;
    }

    public double getWeight() {
        double result = 0;

        if (fruits.size() > 0) {
            result = fruits.size() * fruits.get(0).getWeight();
        }
        return result;
    }

    public String getFruitName() {
        return fruitName;
    }

    public void printBox() {
        System.out.println("Printing box");
        for (T fruit : this.fruits ) {
            System.out.print(fruit.getName() + " ");
        }
        System.out.println();
        System.out.println("Box weigth is: " + this.getWeight());
    }

    public boolean compareTo(Box<?> box) {
        return box.getWeight() == this.getWeight();
    }

    public void moveToAnyBox(Box<?> box) throws InvalidBoxFruitTypeException {
        if (!box.getFruitName().equals(this.getFruitName())) {
            throw new InvalidBoxFruitTypeException("Boxes have different fruits!");
        }
        ArrayList<T> targetBoxFruits = (ArrayList<T>) box.getFruits();
        targetBoxFruits.addAll(this.fruits);
        this.fruits.clear();
    }

    public void moveToSameTypeBox(Box<T> box) throws InvalidBoxFruitTypeException {
        box.getFruits().addAll(this.fruits);
        this.fruits.clear();
    }

}