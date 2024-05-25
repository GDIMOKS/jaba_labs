package lab3;

import java.util.Map;

public class NumberNode extends Node {
    private final double value;

    NumberNode(double value) {
        this.value = value;
    }

    @Override
    public double evaluate(Map<String, Double> variables) {
        return value;
    }

    @Override
    public Node simplify(Map<Node, Node> cache) {
        return this;
    }

    @Override
    public Node deepCopy() {
        return new NumberNode(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        NumberNode that = (NumberNode) obj;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(value);
    }
}

