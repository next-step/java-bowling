package bowling.state.domain;

import java.util.Arrays;

public enum State {
    STRIKE("X"),
    SPARE("/"),
    MISS(""),
    GUTTER("-");

    private String result;

    public String getResult() {
        return result;
    }

    State(String result) {
        this.result = result;
    }

}
