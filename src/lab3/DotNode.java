package lab3;

import java.util.ArrayList;
import java.util.List;

public class DotNode {
    // Идентификатор узла
    private final String nodeId;
    // Метка узла
    private final String label;
    // Список идентификаторов узлов, к которым ведут рёбра из данного узла
    private final List<String> edges = new ArrayList<>();

    // Конструктор, инициализирующий идентификатор и метку узла
    public DotNode(String nodeId, String label) {
        this.nodeId = nodeId;
        this.label = label;
    }

    // Метод для добавления ребра к другому узлу
    public void addEdge(String toNodeId) {
        edges.add(toNodeId);
    }

    // Метод для генерации строки, представляющей узел и его связи в формате DOT
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        // Добавляем строку для узла с его меткой
        str.append(String.format("%s[label=\"%s\"]; ", nodeId, label));
        // Добавляем строки для всех рёбер узла
        for (String toNodeId : edges) {
            str.append(String.format("%s -> %s; ", nodeId, toNodeId));
        }
        return str.toString();
    }
}