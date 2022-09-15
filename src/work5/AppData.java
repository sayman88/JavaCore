package work5;

import java.util.Arrays;

public class AppData {

    private String[] header;

    public AppData(String[] header) {
        this.header = header;
    }

    @Override
    public String toString() {
        return Arrays.toString(header);
    }
}