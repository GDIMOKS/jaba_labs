package lab3;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Parser {
    private String input;
    private int pos;
    private Map<String,ConstantNode> availableConstants = Map.of("E", new ConstantNode(Math.E),
            "PI", new ConstantNode(Math.PI),
            "PHI",new ConstantNode(1.618033988749895)
    );

    public Node parse(String input) {
        this.input = input.replaceAll("\\s+", ""); // Удаление всех пробелов
        this.pos = 0;
        return parseExpression();
    }

    private Node parseExpression() {
        Node result = parseTerm();
        while (pos < input.length()) {
            char currentChar = input.charAt(pos);
            if (currentChar == '+' || currentChar == '-') {
                pos++;
                Node right = parseTerm();
                result = new BinaryOperationNode(result, right, currentChar);
            } else {
                break;
            }
        }
        return result;
    }

    private Node parseTerm() {
        Node result = parseFactor();
        while (pos < input.length()) {
            char currentChar = input.charAt(pos);
            if (currentChar == '*' || currentChar == '/') {
                pos++;
                Node right = parseFactor();
                result = new BinaryOperationNode(result, right, currentChar);
            } else {
                break;
            }
        }
        return result;
    }

    private Node parseFactor() {
        if (pos >= input.length()) {
            throw new IllegalArgumentException("Неожиданное окончание ввода");
        }

        char currentChar = input.charAt(pos);
        if (currentChar == '(') {
            pos++;
            Node result = parseExpression();
            if (pos >= input.length() || input.charAt(pos) != ')') {
                throw new IllegalArgumentException("Несовпадающие круглые скобки");
            }
            pos++;
            return result;
        } else if (currentChar == '-') {
            pos++;
            return new UnaryOperationNode(parseFactor(), '-');
        } else if (Character.isDigit(currentChar) || currentChar == '.') {
            return parseNumber();
        } else if (Character.isLetter(currentChar)) {
            if (Character.isLowerCase(currentChar)) {
                return parseVariableOrFunction();
            } else {
                return parseConstant();
            }
        } else {
            throw new IllegalArgumentException("Неожиданный символ: " + currentChar);
        }
    }

    private Node parseNumber() {
        int startPos = pos;
        while (pos < input.length() && (Character.isDigit(input.charAt(pos)) || input.charAt(pos) == '.')) {
            pos++;
        }
        double value = Double.parseDouble(input.substring(startPos, pos));
        return new NumberNode(value);
    }

    private Node parseVariableOrFunction() {
        int startPos = pos;
        while (pos < input.length() && Character.isLetter(input.charAt(pos))) {
            pos++;
        }
        String name = input.substring(startPos, pos);
        if (pos < input.length() && input.charAt(pos) == '(') {
            pos++;
            Node[] arguments = parseArguments();
            if (pos >= input.length() || input.charAt(pos) != ')') {
                throw new IllegalArgumentException("Несовпадающие круглые скобки при вызове функции");
            }
            pos++;
            return new FunctionNode(name, arguments);
        } else {
            return new VariableNode(name);
        }
    }

    private Node parseConstant() {
        int startPos = pos;
        while (pos < input.length() && Character.isLetter(input.charAt(pos))) {
            pos++;
        }
        String name = input.substring(startPos, pos);

        Collection<String> collection= availableConstants.keySet();
        for (String key : collection) {
            if (key.equals(name)) {
                return availableConstants.get(key);
            }
        }

        throw new IllegalArgumentException("Неизвестная константа");
    }

    private Node[] parseArguments() {
        Stack<Node> arguments = new Stack<>();
        while (pos < input.length() && input.charAt(pos) != ')') {
            arguments.add(parseExpression());
            if (pos < input.length() && input.charAt(pos) == ',') {
                pos++;
            }
        }
        return arguments.toArray(new Node[0]);
    }
}
