package lab3;

import java.util.Map;


public class Main {
    public static void main(String[] args) {
        Lab3();
    }

    public static void Lab3() {
//        Task1();
//        System.out.println("\n////////////////////////////////////\n");
//        Task2();
//        System.out.println("\n////////////////////////////////////\n");
        Task3();
    }

    public static void Task1() {
        Parser parser = new Parser();
        Node f = parser.parse("x + 2 * (y - -3 + z*z) + cos(0)");
        Map<String, Double> variables = Map.of("x", 2.0, "y", 3.0, "z", 4.0);
        System.out.println(f.evaluate(variables));

        Node f2 = parser.parse("3 + 5 * 2");
        System.out.println(f2.evaluate(Map.of()));

        Node f3 = parser.parse("cos(0)");
        System.out.println(f3.evaluate(Map.of()));

        Node f4 = parser.parse("-5 + 3");
        System.out.println(f4.evaluate(Map.of()));
    }

    public static void Task2() {
        Parser parser = new Parser();
        Node f = parser.parse("(x + 1) * (x + 1) + (x + 1) * 3 + (x + 1) * (x + 1)");
        Node g = Simplifier.simplify(f);

        Map<String, Double> variables = Map.of("x", 1.0);
        double resultF = f.evaluate(variables);
        double resultG = g.evaluate(variables);

        System.out.println("Выражение f:");
        System.out.println(f);

        System.out.println("Упрощенное выражение g:");
        System.out.println(g);

        System.out.println("Результат выражения f:");
        System.out.println(resultF);

        System.out.println("Результат упрощенного выражения g:");
        System.out.println(resultG);
    }

    public static void Task3() {
        Parser parser = new Parser();
        Node f = parser.parse("(x + 1) * (x + 1)");
        Node g = Simplifier.simplify(f);

        Map<String, Double> variables = Map.of("x", 2.0);

        double resultF = f.evaluate(variables);
        double resultG = g.evaluate(variables);

        System.out.println("Вычисленное значение f: " + resultF);
        System.out.println("Вычисленное значение g: " + resultG);

        DotGenerator dotF = new DotGenerator(f);
        DotGenerator dotG = new DotGenerator(g);

        System.out.println("DOT-граф f:");
        System.out.println(dotF);

        System.out.println("DOT-граф g:");
        System.out.println(dotG);
    }

}
