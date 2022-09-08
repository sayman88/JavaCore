package work3;

public class AbstractArray<T> {
    T[] array;

    public AbstractArray(T[] array) {
        this.array = array;
    }

    public void swapValues(int i, int j) {
        T obj = array[i];
        array[i] = array[j];
        array[j] = obj;
    }

    public void printArray() {
        System.out.println("Printing array:");
        for (T t : array) {
            System.out.print(t + " ");
        }
        System.out.println();
    }
    public static void testArraySwap(AbstractArray<?> abstractArray, int i, int j) {
        abstractArray.printArray();
        abstractArray.swapValues(i, j);
        abstractArray.printArray();
    }

}