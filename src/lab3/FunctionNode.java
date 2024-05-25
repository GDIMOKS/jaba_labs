package lab3;

import java.util.Map;

public class FunctionNode extends Node {
    private final String functionName;
    private final Node[] arguments;

    FunctionNode(String functionName, Node... arguments) {
        this.functionName = functionName;
        this.arguments = arguments;
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

    @Override
    public Node simplify(Map<Node, Node> cache) {
        Node[] simplifiedArguments = new Node[arguments.length];
        for (int i = 0; i < arguments.length; i++) {
            simplifiedArguments[i] = arguments[i].simplify(cache);
        }

        Node simplifiedNode = new FunctionNode(functionName, simplifiedArguments);
        if (cache.containsKey(simplifiedNode)) {
            return cache.get(simplifiedNode);
        }

        if (functionName.equals("pow") && simplifiedArguments[0] instanceof NumberNode && simplifiedArguments[1] instanceof NumberNode) {
            double base = ((NumberNode) simplifiedArguments[0]).evaluate(null);
            double exponent = ((NumberNode) simplifiedArguments[1]).evaluate(null);
            Node resultNode = new NumberNode(Math.pow(base, exponent));
            cache.put(simplifiedNode, resultNode);
            return resultNode;
        }
        if (functionName.equals("cos") && simplifiedArguments[0] instanceof NumberNode) {
            double angle = ((NumberNode) simplifiedArguments[0]).evaluate(null);
            Node resultNode = new NumberNode(Math.cos(angle));
            cache.put(simplifiedNode, resultNode);
            return resultNode;
        }

        cache.put(simplifiedNode, simplifiedNode);
        return simplifiedNode;
    }

    @Override
    public Node deepCopy() {
        Node[] copiedArguments = new Node[arguments.length];
        for (int i = 0; i < arguments.length; i++) {
            copiedArguments[i] = arguments[i].deepCopy();
        }
        return new FunctionNode(functionName, copiedArguments);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        FunctionNode that = (FunctionNode) obj;
        if (!functionName.equals(that.functionName)) return false;
        if (arguments.length != that.arguments.length) return false;
        for (int i = 0; i < arguments.length; i++) {
            if (!arguments[i].equals(that.arguments[i])) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = functionName.hashCode();
        for (Node argument : arguments) {
            result = 31 * result + argument.hashCode();
        }
        return result;
    }
}
