package bowling.domain;

public enum State {

    MISS("."),
    GUTTER("-"),
    SPARE("/"),
    STRIKE("X"),
    FINAL_DOUBLE("X:X"),
    FINAL_TURKEY("X:X:X");

    public String state;

    State(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return this.state;
    }

}
