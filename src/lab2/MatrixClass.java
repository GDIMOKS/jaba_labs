package lab2;

public class MatrixClass<T> {
    private T[][] data; // Двумерный массив элементов кольца

    // Конструктор класса
    public MatrixClass(T[][] data) {
        this.data = data;
    }

    // Метод для получения элемента матрицы по индексам
    public T getElement(int row, int col) {
        return data[row][col];
    }

    // Метод для установки значения элемента матрицы по индексам
    public void setElement(int row, int col, T value) {
        data[row][col] = value;
    }

    // Метод для получения количества строк матрицы
    public int getRowCount() {
        return data.length;
    }

    // Метод для получения количества столбцов матрицы
    public int getColumnCount() {
        return data[0].length;
    }

}
