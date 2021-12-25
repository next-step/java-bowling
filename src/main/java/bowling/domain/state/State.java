package bowling.domain.state;

public interface State {
    State bowl(int pinCount);
    boolean isFinished();
    boolean hasBonus();
}
