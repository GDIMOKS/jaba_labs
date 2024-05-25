package lab3;
import java.util.HashMap;
import java.util.Map;

import java.util.HashMap;
import java.util.Map;

public class Simplifier {

    public static Node simplify(Node node) {
        Map<String, Node> uniqueNodes = new HashMap<>();
        return simplifyNode(node, uniqueNodes);
    }

    private static Node simplifyNode(Node node, Map<String, Node> uniqueNodes) {
        if (node instanceof BinaryOperationNode) {
            BinaryOperationNode binaryNode = (BinaryOperationNode) node;
            Node left = simplifyNode(binaryNode.getLeft(), uniqueNodes);
            Node right = simplifyNode(binaryNode.getRight(), uniqueNodes);
            return new BinaryOperationNode(left, right, binaryNode.getOperator());
        } else if (node instanceof UnaryOperationNode) {
            UnaryOperationNode unaryNode = (UnaryOperationNode) node;
            Node child = simplifyNode(unaryNode.getOperand(), uniqueNodes);
            return new UnaryOperationNode(child, unaryNode.getOperator());
        } else if (node instanceof FunctionNode) {
            FunctionNode functionNode = (FunctionNode) node;
            Node[] arguments = new Node[functionNode.getArguments().length];
            for (int i = 0; i < functionNode.getArguments().length; i++) {
                arguments[i] = simplifyNode(functionNode.getArguments()[i], uniqueNodes);
            }
            return new FunctionNode(functionNode.getFunctionName(), arguments);
        } else if (node instanceof VariableNode || node instanceof ConstantNode || node instanceof NumberNode) {
            String key = node.toString();
            if (!uniqueNodes.containsKey(key)) {
                uniqueNodes.put(key, node);
            }
            return uniqueNodes.get(key);
        }
        return null;
    }
}
