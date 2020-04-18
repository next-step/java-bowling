package bowling.domain.pitch;

interface State {
    State bowl(int pinCount);
}
