package lab3;

import java.util.Map;

public class UnaryOperationNode extends Node {
    private final Node operand;
    private final char operator;

    UnaryOperationNode(Node operand, char operator) {
        this.operand = operand;
        this.operator = operator;
    }

    @Override
    public double evaluate(Map<String, Double> variables) {
        double operandValue = operand.evaluate(variables);
        switch (operator) {
            case '-': return -operandValue;
            default: throw new IllegalArgumentException("Неизвестный оператор: " + operator);
        }
    }
}
