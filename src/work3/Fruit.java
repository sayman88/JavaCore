package work3;

public class Fruit {
    private double weight;
    private String name;

    public Fruit(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }
}
