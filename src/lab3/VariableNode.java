package lab3;

import java.util.Map;

public class VariableNode implements Node {
    private final String name;

    VariableNode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public double evaluate(Map<String, Double> variables) {
        if (variables.containsKey(name)) {
            return variables.get(name);
        }
        throw new IllegalArgumentException("Variable " + name + " not defined");
    }

    @Override
    public String toString() {
        return name;
    }
}
