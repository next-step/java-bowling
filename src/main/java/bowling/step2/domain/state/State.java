package bowling.step2.domain.state;

public interface State {
    int COUNT_OF_MAX_PINS = 10;
    
    State bowl(int fallenPins);
}
