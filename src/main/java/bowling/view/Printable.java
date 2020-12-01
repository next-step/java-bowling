package bowling.view;

public abstract class Printable {
    static final String lineSeparator = System.lineSeparator();

    static void print(Object obj) {
        System.out.print(obj);
    }

    public abstract void print();
}
