package bowling.model.state;

public interface State {
    State bowl(int knockedDownPin);
    boolean isFinished();
}
