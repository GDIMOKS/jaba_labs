package lab3;

import java.util.Map;

public class UnaryOperationNode implements Node {
    private final Node operand;
    private final char operator;

    UnaryOperationNode(Node operand, char operator) {
        this.operand = operand;
        this.operator = operator;
    }

    public Node getOperand() {
        return operand;
    }
    public char getOperator() {
        return operator;
    }
    @Override
    public double evaluate(Map<String, Double> variables) {
        double operandValue = operand.evaluate(variables);
        switch (operator) {
            case '-': return -operandValue;
            default: throw new IllegalArgumentException("Неизвестный оператор: " + operator);
        }
    }

    @Override
    public String toString() {
        return operator + operand.toString();
    }
}
