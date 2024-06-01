package lab3;

import java.util.*;

public class DotGenerator {
    // Список для хранения всех вершин DotNode
    private final List<DotNode> dotNodes = new ArrayList<>();
    // Карта для сопоставления узлов дерева с их идентификаторами
    private final HashMap<Node, String> nodeMap = new HashMap<>();

    // Конструктор, который принимает корневой узел дерева и генерирует его DOT представление
    public DotGenerator(Node root) {
        IdGenerator idGen = new IdGenerator();
        generateDot(root, idGen);
    }

    // Метод для получения метки узла в зависимости от его типа
    private String getNodeLabel(Node node) {
        return switch (node) {
            case BinaryOperationNode binaryOperationNode -> binaryOperationNode.getOperator() + "";
            case UnaryOperationNode unaryOperationNode -> unaryOperationNode.getOperator() + "";
            case FunctionNode functionNode -> functionNode.getFunctionName();
            case VariableNode variableNode -> variableNode.getName();
            case NumberNode numberNode -> Double.toString(numberNode.getValue());
            case null, default -> node.toString();
        };
    }

    // Метод для рекурсивного генерации DOT представления узла и его потомков
    private String generateDot(Node node, IdGenerator counter) {
        if (node == null) {
            return null;
        }

        // Если узел уже обработан, возвращаем его идентификатор
        if (nodeMap.containsKey(node)) {
            return nodeMap.get(node);
        }

        // Генерируем новый идентификатор для узла
        String nodeId = counter.nextId();
        // Сохраняем соответствие узла и его идентификатора
        nodeMap.put(node, nodeId);
        // Создаем новый DotNode с меткой узла
        DotNode dotNode = new DotNode(nodeId, getNodeLabel(node));
        // Добавляем DotNode в список
        dotNodes.add(dotNode);

        // Обрабатываем потомков узла в зависимости от его типа
        switch (node) {
            case BinaryOperationNode binOpNode -> {
                String leftId = generateDot(binOpNode.getLeft(), counter);
                String rightId = generateDot(binOpNode.getRight(), counter);
                if (leftId != null) {
                    dotNode.addEdge(leftId);
                }
                if (rightId != null) {
                    dotNode.addEdge(rightId);
                }
            }
            case UnaryOperationNode unOpNode -> {
                String operandId = generateDot(unOpNode.getOperand(), counter);
                if (operandId != null) {
                    dotNode.addEdge(operandId);
                }
            }
            case FunctionNode funcNode -> {
                for (Node arg : funcNode.getArguments()) {
                    String argId = generateDot(arg, counter);
                    if (argId != null) {
                        dotNode.addEdge(argId);
                    }
                }
            }
            default -> {
                // Если узел не поддерживает потомков, ничего не делаем
            }
        }

        // Возвращаем идентификатор текущего узла
        return nodeId;
    }

    // Метод для генерации строки представления графа в формате DOT
    @Override
    public String toString() {
        StringBuilder graph = new StringBuilder();

        for (DotNode dotNode : dotNodes) {
            graph.append(dotNode.toString());
            graph.append('\n');
        }

        return "digraph G {\n" + graph + "}";
    }
}
