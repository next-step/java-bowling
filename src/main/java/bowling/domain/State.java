package bowling.domain;

public enum State {

    MISS("."),
    GURTER("-"),
    SPARE("/"),
    STRIKE("X"),
    DOUBLE("XX"),
    TURKEY("XXX");

    public String state;

    State(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return this.state;
    }
}
