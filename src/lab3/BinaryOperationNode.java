package lab3;

import java.util.Map;

public class BinaryOperationNode extends Node {
    private final Node left;
    private final Node right;
    private final char operator;

    BinaryOperationNode(Node left, Node right, char operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public double evaluate(Map<String, Double> variables) {
        double leftValue = left.evaluate(variables);
        double rightValue = right.evaluate(variables);
        return switch (operator) {
            case '+' -> leftValue + rightValue;
            case '-' -> leftValue - rightValue;
            case '*' -> leftValue * rightValue;
            case '/' -> leftValue / rightValue;
            default -> throw new IllegalArgumentException("Неизвестный оператор: " + operator);
        };
    }

    @Override
    public Node simplify(Map<Node, Node> cache) {
        Node simplifiedLeft = left.simplify(cache);
        Node simplifiedRight = right.simplify(cache);

        Node simplifiedNode = new BinaryOperationNode(simplifiedLeft, simplifiedRight, operator);
        if (cache.containsKey(simplifiedNode)) {
            return cache.get(simplifiedNode);
        }

        if (simplifiedLeft.equals(simplifiedRight)) {
            cache.put(simplifiedNode, simplifiedLeft);
            return simplifiedLeft;
        }

        if (simplifiedLeft instanceof NumberNode && simplifiedRight instanceof NumberNode) {
            double leftValue = ((NumberNode) simplifiedLeft).evaluate(null);
            double rightValue = ((NumberNode) simplifiedRight).evaluate(null);
            Node resultNode;
            switch (operator) {
                case '+': resultNode = new NumberNode(leftValue + rightValue); break;
                case '-': resultNode = new NumberNode(leftValue - rightValue); break;
                case '*': resultNode = new NumberNode(leftValue * rightValue); break;
                case '/': resultNode = new NumberNode(leftValue / rightValue); break;
                default: throw new IllegalArgumentException("Unknown operator: " + operator);
            }
            cache.put(simplifiedNode, resultNode);
            return resultNode;
        }

        cache.put(simplifiedNode, simplifiedNode);
        return simplifiedNode;
    }

    @Override
    public Node deepCopy() {
        return new BinaryOperationNode(left.deepCopy(), right.deepCopy(), operator);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BinaryOperationNode that = (BinaryOperationNode) obj;
        return operator == that.operator &&
                left.equals(that.left) &&
                right.equals(that.right);
    }

    @Override
    public int hashCode() {
        int result = left.hashCode();
        result = 31 * result + right.hashCode();
        result = 31 * result + Character.hashCode(operator);
        return result;
    }
}
