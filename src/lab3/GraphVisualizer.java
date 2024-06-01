package lab3;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GraphVisualizer {
    public static void generateImage(DotGenerator dot, String outputFileName) throws IOException, InterruptedException {
        // �������� ���������� ����� ��� �������� DOT-�������� �����
        Path dotFile = Files.createTempFile("graph", ".txt");
        try (BufferedWriter writer = Files.newBufferedWriter(dotFile)) {
            writer.write(dot.toString());
        }

        // ��������� ����������� ���� ������� � ���� � Graphviz
        String projectRoot = Paths.get("").toAbsolutePath().toString();
        String dotPath = Paths.get(projectRoot, "Graphviz-11.0.0-win64", "bin", "dot.exe").toString();

        // ������ �������� Graphviz ��� ��������� �����������
        ProcessBuilder processBuilder = new ProcessBuilder(dotPath, "-Tpng", dotFile.toString(), "-o", outputFileName);
        Process process = processBuilder.start();

        // ������ � ����� ������������ ������ ��������
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            reader.lines().forEach(System.out::println);
        }

        // �������� ���������� ��������
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Graphviz ���������� � ����� " + exitCode);
        }

        // �������� ������������� ���������� �����������
        if (ImageIO.read(new File(outputFileName)) == null) {
            throw new IOException("�� ������� ��������� ��������� �����������.");
        }
    }

    public static void displayImages(String... imagePaths) {
        JFrame frame = new JFrame("����������� ������");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(1, imagePaths.length));

        // ���������� ������� � �������������
        for (String imagePath : imagePaths) {
            frame.add(new ImagePanel(imagePath));
        }

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static class ImagePanel extends JPanel {
        private final Image image;

        public ImagePanel(String imagePath) {
            image = new ImageIcon(imagePath).getImage();
            setPreferredSize(new Dimension(image.getWidth(null), image.getHeight(null)));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this);
        }
    }
}
