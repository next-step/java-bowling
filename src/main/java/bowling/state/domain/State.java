package bowling.state.domain;

public enum State {
    STRIKE("X"),
    SPARE("/"),
    MISS(""),
    GUTTER("-"),
    PASS("");

    private String mark;

    public String getMark() {
        return mark;
    }

    State(String mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return mark;
    }
}
