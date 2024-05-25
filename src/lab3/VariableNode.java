package lab3;

import java.util.Map;

public class VariableNode extends Node {
    private final String name;

    VariableNode(String name) {
        this.name = name;
    }

    @Override
    public double evaluate(Map<String, Double> variables) {
        if (variables.containsKey(name)) {
            return variables.get(name);
        }
        throw new IllegalArgumentException("Переменная " + name + " не объявлена");
    }

    @Override
    public Node simplify(Map<Node, Node> cache) {
        return this;
    }

    @Override
    public Node deepCopy() {
        return new VariableNode(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        VariableNode that = (VariableNode) obj;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
