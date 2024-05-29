package lab3;

import java.util.HashMap;
import java.util.Map;

public class BinaryOperationNode implements Node {
    private final Node left;
    private final Node right;
    private final char operator;
    private Map<String, Double> values;

    BinaryOperationNode(Node left, Node right, char operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
        this.values = new HashMap<>();
    }

    public Node getLeft() {
        return left;
    }
    public Node getRight() {
        return right;
    }
    public char getOperator() {
        return operator;
    }
    public Map<String, Double> getResult() {
        return values;
    }
    @Override
    public double evaluate(Map<String, Double> variables) {

        if (!values.isEmpty())
        {
            String key = variables.toString();
            if (values.containsKey(key))
                return values.get(key);
        }

        double leftValue = left.evaluate(variables);
        double rightValue = right.evaluate(variables);
        double result;

        switch (operator) {
            case '+':
                result = leftValue + rightValue;
                break;
            case '-':
                result =  leftValue - rightValue;
                break;
            case '*':
                result = leftValue * rightValue;
                break;
            case '/':
                result = leftValue / rightValue;
                break;
            default: throw new IllegalArgumentException("Неизвестный оператор: " + operator);
        }

        values.put(variables.toString(), result);

        return result;
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " " + operator + " " + right.toString() + ")";
    }
}
