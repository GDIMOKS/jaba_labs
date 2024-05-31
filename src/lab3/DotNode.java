package lab3;

import java.util.ArrayList;
import java.util.List;

public class DotNode {
    // ������������� ����
    private final String nodeId;
    // ����� ����
    private final String label;
    // ������ ��������������� �����, � ������� ����� ���� �� ������� ����
    private final List<String> edges = new ArrayList<>();

    // �����������, ���������������� ������������� � ����� ����
    public DotNode(String nodeId, String label) {
        this.nodeId = nodeId;
        this.label = label;
    }

    // ����� ��� ���������� ����� � ������� ����
    public void addEdge(String toNodeId) {
        edges.add(toNodeId);
    }

    // ����� ��� ��������� ������, �������������� ���� � ��� ����� � ������� DOT
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        // ��������� ������ ��� ���� � ��� ������
        str.append(String.format("%s[label=\"%s\"]; ", nodeId, label));
        // ��������� ������ ��� ���� ���� ����
        for (String toNodeId : edges) {
            str.append(String.format("%s -> %s; ", nodeId, toNodeId));
        }
        return str.toString();
    }
}