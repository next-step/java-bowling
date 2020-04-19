package bowling.domain.pitch;

interface State {
    State bowl(Pin pin);
}
