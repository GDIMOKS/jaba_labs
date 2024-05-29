package lab3;
import java.util.ArrayList;
import java.util.List;

public class Simplifier {
    public static Node simplify(Node expression) {
        List<Node> uniqueNodes = new ArrayList<>();
        return simplifyExpression(expression, uniqueNodes);
    }

    private static Node simplifyExpression(Node expression, List<Node> uniqueNodes) {
        for (Node node : uniqueNodes) {
            if (expression.toString().equals(node.toString())) {
                return node;
            }
        }

        if (expression instanceof BinaryOperationNode) {
            BinaryOperationNode binaryNode = (BinaryOperationNode) expression;
            Node left = simplifyExpression(binaryNode.getLeft(), uniqueNodes);
            Node right = simplifyExpression(binaryNode.getRight(), uniqueNodes);
            Node simplifiedNode = new BinaryOperationNode(left, right, binaryNode.getOperator());
            uniqueNodes.add(simplifiedNode);
            return simplifiedNode;
        } else if (expression instanceof UnaryOperationNode) {
            UnaryOperationNode unaryNode = (UnaryOperationNode) expression;
            Node operand = simplifyExpression(unaryNode.getOperand(), uniqueNodes);
            Node simplifiedNode = new UnaryOperationNode(operand, unaryNode.getOperator());
            uniqueNodes.add(simplifiedNode);
            return simplifiedNode;
        } else if (expression instanceof FunctionNode) {
            FunctionNode functionNode = (FunctionNode) expression;
            Node[] arguments = functionNode.getArguments();
            Node[] simplifiedArguments = new Node[arguments.length];
            for (int i = 0; i < arguments.length; i++) {
                simplifiedArguments[i] = simplifyExpression(arguments[i], uniqueNodes);
            }
            Node simplifiedNode = new FunctionNode(functionNode.getName(), simplifiedArguments);
            uniqueNodes.add(simplifiedNode);
            return simplifiedNode;
        } else {
            uniqueNodes.add(expression);
            return expression;
        }
    }
}