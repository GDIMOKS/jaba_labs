package lab3;

import java.util.Map;

public class ConstantNode implements Node {
    private final double value;

    ConstantNode(double value) {
        this.value = value;
    }

    @Override
    public double evaluate(Map<String, Double> variables) {
        return value;
    }
}
