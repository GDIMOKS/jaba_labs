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

    @Override
    public Node simplify(Map<Node, Node> cache) {
        Node simplifiedOperand = operand.simplify(cache);

        Node simplifiedNode = new UnaryOperationNode(simplifiedOperand, operator);
        if (cache.containsKey(simplifiedNode)) {
            return cache.get(simplifiedNode);
        }

        if (simplifiedOperand instanceof NumberNode) {
            double operandValue = ((NumberNode) simplifiedOperand).evaluate(null);
            Node resultNode;
            switch (operator) {
                case '-': resultNode = new NumberNode(-operandValue); break;
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
        return new UnaryOperationNode(operand.deepCopy(), operator);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UnaryOperationNode that = (UnaryOperationNode) obj;
        return operator == that.operator &&
                operand.equals(that.operand);
    }

    @Override
    public int hashCode() {
        int result = operand.hashCode();
        result = 31 * result + Character.hashCode(operator);
        return result;
    }
}
