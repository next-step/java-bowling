package bowling.view;

public enum ResultMark {

    STRIKE("X"),
    SPARE("/"),
    GUTTER("-"),
    EMPTY(" ");

    private final String mark;

    ResultMark(String mark) {
        this.mark = mark;
    }

    public String getMark() {
        return this.mark;
    }

}
