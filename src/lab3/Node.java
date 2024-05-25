package lab3;

import java.util.Map;

public abstract class Node {
    protected abstract double evaluate(Map<String, Double> variables);
//    protected abstract Node simplify();

    protected abstract Node simplify(Map<Node, Node> cache);
    protected abstract Node deepCopy();
}
