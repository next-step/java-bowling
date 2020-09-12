package bowling.state.domain;

public enum State {
    STRIKE("X"),
    SPARE("/"),
    MISS(""),
    GUTTER("-"),
    PASS("");

    private String result;

    public String getResult() {
        return result;
    }

    State(String result) {
        this.result = result;
    }

}
