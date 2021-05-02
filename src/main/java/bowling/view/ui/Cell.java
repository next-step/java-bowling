package bowling.view.ui;

public final class Cell {

    private static final String EMPTY_STRING = "";

    private final String data;
    private final Align align;

    public Cell(String data, Align align) {
        this.data = data;
        this.align = align;
    }

    public String data() {
        if (data == null) {
            return EMPTY_STRING;
        }
        return data;
    }

    public Align getAlign() {
        return align;
    }
}
