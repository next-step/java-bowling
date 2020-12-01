package bowling.view;

class Printable {
    static final String lineSeparator = System.lineSeparator();
    private final StringBuilder sb = new StringBuilder();

    void append(Object obj) {
        sb.append(obj);
    }

    void print() {
        System.out.print(sb);
    }
}
