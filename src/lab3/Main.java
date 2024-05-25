package lab3;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Parser parser = new Parser();
        Node f = parser.parse("x + 2 * (y - -3 + z*z) + cos(0)");
        Map<String, Double> variables = Map.of("x", 2.0, "y", 3.0, "z", 4.0);
        System.out.println(f.evaluate(variables));

        Node f2 = parser.parse("3 + 5 * 2");
        System.out.println(f2.evaluate(Map.of()));

        Node f3 = parser.parse("cos(0)");
        System.out.println(f3.evaluate(Map.of()));
    }
}
