//package lab2.part2;
//
//import java.util.Map;
//
//public class Parser {
//    private String expression;
//    private int index;
//    private Map<String, Double> constants;
//
//    public Parser(String expression) {
//        this.expression = expression.replaceAll("\\s", ""); // Удаляем пробелы
//        this.index = 0;
//        this.constants = Map.of("E", Math.E, "PI", Math.PI); // Задаем значения для констант
//    }
//
//    public Expression parse() {
//        return parseExpression();
//    }
//
//    private Expression parseExpression() {
//        return parseAddition();
//    }
//
//    private Expression parseAddition() {
//        Expression left = parseMultiplication();
//        while (index < expression.length()) {
//            char operator = expression.charAt(index);
//            if (operator != '+' && operator != '-') {
//                break;
//            }
//            index++;
//            Expression right = parseMultiplication();
//            if (operator == '+') {
//                left = new Addition(left, right);
//            } else {
//                left = new Subtraction(left, right);
//            }
//        }
//        return left;
//    }
//
//    private Expression parseMultiplication() {
//        Expression left = parsePrimary();
//        while (index < expression.length()) {
//            char operator = expression.charAt(index);
//            if (operator != '*' && operator != '/') {
//                break;
//            }
//            index++;
//            Expression right = parsePrimary();
//            if (operator == '*') {
//                left = new Multiplication(left, right);
//            } else {
//                left = new Division(left, right);
//            }
//        }
//        return left;
//    }
//
//    private Expression parsePrimary() {
//        if (index < expression.length()) {
//            char nextChar = expression.charAt(index);
//            if (Character.isDigit(nextChar)) {
//                return parseNumber();
//            } else if (Character.isLetter(nextChar)) {
//                return parseIdentifier();
//            } else if (nextChar == '(') {
//                index++;
//                Expression expression = parseExpression();
//                if (index < expression.length() && expression.charAt(index) == ')') {
//                    index++;
//                    return expression;
//                } else {
//                    // Обработка ошибки: неправильное закрытие скобок
//                    throw new IllegalArgumentException("Неправильное закрытие скобок");
//                }
//            }
//        }
//        // Обработка ошибки: неверное выражение
//        throw new IllegalArgumentException("Неверное выражение");
//    }
//
//    private Expression parseNumber() {
//        StringBuilder sb = new StringBuilder();
//        while (index < expression.length() && (Character.isDigit(expression.charAt(index)) || expression.charAt(index) == '.')) {
//            sb.append(expression.charAt(index));
//            index++;
//        }
//        return new Number(Double.parseDouble(sb.toString()));
//    }
//
//    private Expression parseIdentifier() {
//        StringBuilder sb = new StringBuilder();
//        while (index < expression.length() && Character.isLetterOrDigit(expression.charAt(index))) {
//            sb.append(expression.charAt(index));
//            index++;
//        }
//        String identifier = sb.toString();
//        if (constants.containsKey(identifier)) {
//            return new Number(constants.get(identifier));
//        } else {
//            return new Variable(identifier);
//        }
//    }
//}
