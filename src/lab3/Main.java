package lab3;

import java.util.HashMap;
import java.util.Map;

//import static lab3.Simplifier.simplify;

public class Main {
    public static void main(String[] args) {
        Lab3();
    }

    public static void Lab3() {
        Task1();
        System.out.println("\n////////////////////////////////////\n");
//        Task2();
//        System.out.println("\n////////////////////////////////////\n");
//        Task3();
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
        // Исходное выражение
        Node f = parser.parse("(x + 1) * (x + 1)");
        System.out.println(f.evaluate(Map.of("x", 1.0)));
        // Упрощенное выражение с удаленными дубликатами подвыражений
//        Node g = simplify(f);

        // Вывод результата упрощенного выражения
//        System.out.println(g.evaluate(Map.of("x", 1.0)));
    }

    public static void Task3() {


    }

}
