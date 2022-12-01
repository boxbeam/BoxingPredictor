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
        Row withoutTarget = input.removeLast();
        double output = predict(withoutTarget);
        return Math.abs(withoutTarget.data()[withoutTarget.data().length - 1] - output);
    }

    private double slope(Row input, int parameter) {
        double before = error(input);
        double beforeParameter = weights[parameter];
        weights[parameter] += 0.01;
        double after = error(input);
        weights[parameter] = beforeParameter;
        return (after - before) / 0.01;
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

    private void train(Row row) {
        double[] slopes = new double[weights.length];
        for (int i = 0; i < weights.length; i++) {
            slopes[i] = slope(row, i);
        }
        double sum = Arrays.stream(slopes).sum();
        double total = 0.01 / sum;
        for (int i = 0; i < weights.length; i++) {
            weights[i] += -slopes[i] * total;
        }
    }

}
