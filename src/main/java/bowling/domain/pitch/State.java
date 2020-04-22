package bowling.domain.pitch;

interface State {
    State bowl(Pin pin);

    boolean isStrike();

    boolean isSpare();
}
