package bowling.view.printable;

public abstract class Printable {
    private static final String lineSeparator = System.lineSeparator();

    static void print(Object obj) {
        System.out.print(lineSeparator + obj);
    }

    public abstract void print();
}
