package logisticregression;

import java.util.Arrays;

public class LogisticRegression {

    private double[] weights;

    public LogisticRegression(int size) {
        weights = new double[size + 1];
        Arrays.fill(weights, 1);
    }

    public double predict(Row input) {
        checkSize(input.data().length);
        double base = weights[0];
        for (int i = 0; i < input.data().length; i++) {
            base += weights[i + 1] * Math.log(input.data()[i]);
        }
        return base;
    }

    private double error(Row input) {

    }

    private void checkSizeTraining(int size) {
        if (size != weights.length) {
            throw new IllegalStateException("Wrong number of input rows");
        }
    }

    private void checkSize(int size) {
        if (size != weights.length - 1) {
            throw new IllegalStateException("Wrong number of input rows");
        }
    }

    public void train(Table table) {
        checkSize(table.columns());
    }

}
