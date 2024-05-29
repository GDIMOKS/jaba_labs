//package lab3;
//import java.util.HashMap;
//import java.util.Map;
//
//public class Simplifier {
//
//    // Метод упрощения функции
//    public static Node simplify(Node node) {
//        // Создаем карту для отслеживания уникальных узлов
//        Map<String, Node> uniqueNodes = new HashMap<>();
//        // Вызываем вспомогательный метод для упрощения узла
//        return simplifyNode(node, uniqueNodes);
//    }
//
//    private static Node simplifyNode(Node node, Map<String, Node> uniqueNodes, Map<String, Double> variableValues) {
//        // Сначала проверяем, если узел уже упрощенный, то возвращаем его
//        if (uniqueNodes.containsKey(node.toString())) {
//            return uniqueNodes.get(node.toString());
//        }
//
//        // Если узел является бинарной операцией
//        if (node instanceof BinaryOperationNode) {
//            // Получаем ссылку на узел бинарной операции
//            BinaryOperationNode binaryNode = (BinaryOperationNode) node;
//            // Рекурсивно упрощаем левый и правый дочерние узлы
//            Node left = simplifyNode(binaryNode.getLeft(), uniqueNodes, variableValues);
//            Node right = simplifyNode(binaryNode.getRight(), uniqueNodes, variableValues);
//            // Создаем новый узел бинарной операции с упрощенными дочерними узлами
//            BinaryOperationNode simplifiedNode = new BinaryOperationNode(left, right, binaryNode.getOperator());
//            // Добавляем узел в карту уникальных узлов
//            uniqueNodes.put(simplifiedNode.toString(), simplifiedNode);
//            // Возвращаем упрощенный узел
//            return simplifiedNode;
//        }
//        // Если узел является унарной операцией
//        else if (node instanceof UnaryOperationNode) {
//            // Получаем ссылку на узел унарной операции
//            UnaryOperationNode unaryNode = (UnaryOperationNode) node;
//            // Рекурсивно упрощаем дочерний узел
//            Node operand = simplifyNode(unaryNode.getOperand(), uniqueNodes, variableValues);
//            // Создаем новый узел унарной операции с упрощенным дочерним узлом
//            UnaryOperationNode simplifiedNode = new UnaryOperationNode(operand, unaryNode.getOperator());
//            // Добавляем узел в карту уникальных узлов
//            uniqueNodes.put(simplifiedNode.toString(), simplifiedNode);
//            // Возвращаем упрощенный узел
//            return simplifiedNode;
//        }
//        // Если узел является функцией
//        else if (node instanceof FunctionNode) {
//            // Получаем ссылку на узел функции
//            FunctionNode functionNode = (FunctionNode) node;
//            // Рекурсивно упрощаем аргументы функции
//            Node[] arguments = new Node[functionNode.getArguments().length];
//            for (int i = 0; i < functionNode.getArguments().length; i++) {
//                arguments[i] = simplifyNode(functionNode.getArguments()[i], uniqueNodes, variableValues);
//            }
//            // Создаем новый узел функции с упрощенными аргументами
//            FunctionNode simplifiedNode = new FunctionNode(functionNode.getFunctionName(), arguments);
//            // Добавляем узел в карту уникальных узлов
//            uniqueNodes.put(simplifiedNode.toString(), simplifiedNode);
//            // Возвращаем упрощенный узел
//            return simplifiedNode;
//        }
//        // Если узел является переменной, константой или числом
//        else if (node instanceof VariableNode || node instanceof ConstantNode || node instanceof NumberNode) {
//            // Для переменных и констант получаем их значения из карты
//            if (variableValues.containsKey(node.toString())) {
//                return new NumberNode(variableValues.get(node.toString()));
//            } else {
//                // Если значения нет, просто добавляем узел в карту уникальных узлов
//                uniqueNodes.put(node.toString(), node);
//                // Возвращаем оригинальный узел
//                return node;
//            }
//        }
//        // Если узел неизвестного типа, возвращаем null
//        return null;
//    }
//}