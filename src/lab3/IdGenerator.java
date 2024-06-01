package lab3;

public class IdGenerator {
    private int _counter = 0;

    public String nextId() {
        return "n" + _counter++;
    }
}