package lab3;

import java.util.Map;

public class NumberNode implements Node {
    private final double value;

    NumberNode(double value) {
        this.value = value;
    }

    @Override
    public double evaluate(Map<String, Double> variables) {
        return value;
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }
}

