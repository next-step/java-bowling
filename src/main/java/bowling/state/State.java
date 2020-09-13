package bowling.state;

public enum State {
    STRIKE("X"),
    SPARE("/"),
    MISS(""),
    GUTTER("-");

    private String mark;

    State(String mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return mark;
    }
}
