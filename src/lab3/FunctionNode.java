package lab3;

import java.util.Map;

public class FunctionNode extends Node {
    private final String functionName;
    private final Node[] arguments;

    FunctionNode(String functionName, Node... arguments) {
        this.functionName = functionName;
        this.arguments = arguments;
    }

    public String getFunctionName() {
        return functionName;
    }
    public Node[] getArguments() {
        return arguments;
    }
    @Override
    public double evaluate(Map<String, Double> variables) {
        switch (functionName) {
            case "pow":
                return Math.pow(arguments[0].evaluate(variables), arguments[1].evaluate(variables));
            case "cos":
                return Math.cos(arguments[0].evaluate(variables));
            default:
                throw new IllegalArgumentException("Неизвестная функция: " + functionName);
        }
    }
}
