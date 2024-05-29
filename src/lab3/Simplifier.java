//package lab3;
//import java.util.HashMap;
//import java.util.Map;
//
//public class Simplifier {
//
//    // ����� ��������� �������
//    public static Node simplify(Node node) {
//        // ������� ����� ��� ������������ ���������� �����
//        Map<String, Node> uniqueNodes = new HashMap<>();
//        // �������� ��������������� ����� ��� ��������� ����
//        return simplifyNode(node, uniqueNodes);
//    }
//
//    private static Node simplifyNode(Node node, Map<String, Node> uniqueNodes, Map<String, Double> variableValues) {
//        // ������� ���������, ���� ���� ��� ����������, �� ���������� ���
//        if (uniqueNodes.containsKey(node.toString())) {
//            return uniqueNodes.get(node.toString());
//        }
//
//        // ���� ���� �������� �������� ���������
//        if (node instanceof BinaryOperationNode) {
//            // �������� ������ �� ���� �������� ��������
//            BinaryOperationNode binaryNode = (BinaryOperationNode) node;
//            // ���������� �������� ����� � ������ �������� ����
//            Node left = simplifyNode(binaryNode.getLeft(), uniqueNodes, variableValues);
//            Node right = simplifyNode(binaryNode.getRight(), uniqueNodes, variableValues);
//            // ������� ����� ���� �������� �������� � ����������� ��������� ������
//            BinaryOperationNode simplifiedNode = new BinaryOperationNode(left, right, binaryNode.getOperator());
//            // ��������� ���� � ����� ���������� �����
//            uniqueNodes.put(simplifiedNode.toString(), simplifiedNode);
//            // ���������� ���������� ����
//            return simplifiedNode;
//        }
//        // ���� ���� �������� ������� ���������
//        else if (node instanceof UnaryOperationNode) {
//            // �������� ������ �� ���� ������� ��������
//            UnaryOperationNode unaryNode = (UnaryOperationNode) node;
//            // ���������� �������� �������� ����
//            Node operand = simplifyNode(unaryNode.getOperand(), uniqueNodes, variableValues);
//            // ������� ����� ���� ������� �������� � ���������� �������� �����
//            UnaryOperationNode simplifiedNode = new UnaryOperationNode(operand, unaryNode.getOperator());
//            // ��������� ���� � ����� ���������� �����
//            uniqueNodes.put(simplifiedNode.toString(), simplifiedNode);
//            // ���������� ���������� ����
//            return simplifiedNode;
//        }
//        // ���� ���� �������� ��������
//        else if (node instanceof FunctionNode) {
//            // �������� ������ �� ���� �������
//            FunctionNode functionNode = (FunctionNode) node;
//            // ���������� �������� ��������� �������
//            Node[] arguments = new Node[functionNode.getArguments().length];
//            for (int i = 0; i < functionNode.getArguments().length; i++) {
//                arguments[i] = simplifyNode(functionNode.getArguments()[i], uniqueNodes, variableValues);
//            }
//            // ������� ����� ���� ������� � ����������� �����������
//            FunctionNode simplifiedNode = new FunctionNode(functionNode.getFunctionName(), arguments);
//            // ��������� ���� � ����� ���������� �����
//            uniqueNodes.put(simplifiedNode.toString(), simplifiedNode);
//            // ���������� ���������� ����
//            return simplifiedNode;
//        }
//        // ���� ���� �������� ����������, ���������� ��� ������
//        else if (node instanceof VariableNode || node instanceof ConstantNode || node instanceof NumberNode) {
//            // ��� ���������� � �������� �������� �� �������� �� �����
//            if (variableValues.containsKey(node.toString())) {
//                return new NumberNode(variableValues.get(node.toString()));
//            } else {
//                // ���� �������� ���, ������ ��������� ���� � ����� ���������� �����
//                uniqueNodes.put(node.toString(), node);
//                // ���������� ������������ ����
//                return node;
//            }
//        }
//        // ���� ���� ������������ ����, ���������� null
//        return null;
//    }
//}