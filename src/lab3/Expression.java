package lab3;

import java.util.Map;

public abstract class Expression {
    public abstract double evaluate(Map<String, Double> variables);
}

class Variable extends Expression {
    private String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public double evaluate(Map<String, Double> variables) {
        return variables.getOrDefault(name, 0.0);
    }
}

class Number extends Expression {
    private double value;

    public Number(double value) {
        this.value = value;
    }

    @Override
    public double evaluate(Map<String, Double> variables) {
        return value;
    }
}

class Addition extends Expression {
    private Expression left;
    private Expression right;

    public Addition(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public double evaluate(Map<String, Double> variables) {
        return left.evaluate(variables) + right.evaluate(variables);
    }
}

class Subtraction extends Expression {
    private Expression left;
    private Expression right;

    public Subtraction(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public double evaluate(Map<String, Double> variables) {
        return left.evaluate(variables) - right.evaluate(variables);
    }
}

class Multiplication extends Expression {
    private Expression left;
    private Expression right;

    public Multiplication(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public double evaluate(Map<String, Double> variables) {
        return left.evaluate(variables) * right.evaluate(variables);
    }
}

class Division extends Expression {
    private Expression left;
    private Expression right;

    public Division(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public double evaluate(Map<String, Double> variables) {
        double rightValue = right.evaluate(variables);
        if (rightValue == 0) {
            // Обработка ошибки: деление на ноль
            throw new ArithmeticException("Деление на ноль");
        }
        return left.evaluate(variables) / rightValue;
    }
}
