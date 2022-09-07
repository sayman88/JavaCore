package work2;

public class Main {
    public static int ARRAY_SIZE =4;

    public static void main(String[] args){
        String[][] array2rows = {{"1","2","3"}, {"4","5","6"}};
        String[][] array3columns = {{"1","2","3"}, {"4","5","6"}, {"7","8","9"}, {"10","11","12"}};
        String[][] array4by4bad = {{"1","2","3","A"}, {"4","5","6","B"}, {"7","8","9","C"}, {"10","11","12","D"}};
        String[][] array4by4good = {{"1","2","3","4"}, {"1","2","3","4"}, {"1","2","3","4"}, {"1","2","3","4"}};


        runTest("Testing array with 2 rows", array2rows);
        runTest("Testing array with 3 columns", array3columns);
        runTest("Testing array with some non-integer values", array4by4bad);
        runTest("Testing array with integer values", array4by4good);
    }
    public static int processArray(String[][] array) throws MyArraySizeException, MyArrayDataException {
        int rowsCount = array.length;
        if (rowsCount != ARRAY_SIZE) {
            throw new MyArraySizeException("Invalid amount of rows: " + rowsCount);
        }

        int columnsCount = array[0].length;
        if (columnsCount != ARRAY_SIZE) {
            throw new MyArraySizeException("Invalid amount of columns: " + columnsCount);
        }

        int result = 0;
        for(int i = 0; i < ARRAY_SIZE; i++) {
            for(int j = 0; j < ARRAY_SIZE; j++) {
                String s = array[i][j];
                try {
                    int value = Integer.decode(s);
                    result = result + value;
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Field [" + i + "][" + j + "] has invalid value: " + s);
                }
            }
        }
        return result;
    }

    public static void runTest(String testName, String[][] array) {
        int sum;
        System.out.println();
        System.out.println(testName);
        System.out.println("-------------- started --------------");
        try {
            sum = processArray(array);
            System.out.println("Sum is: " + sum);
        } catch (MyArrayDataException | MyArraySizeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println("-------------- finished --------------");
    }






}
