package logisticregression;

import java.util.Arrays;

public record Row(double[] data) {

    public Row removeLast() {
        return new Row(Arrays.copyOfRange(data, 0, data.length - 1));
    }

}