package bowling.model.state;

public interface State {
    State bowl(int countOfPin);

    boolean isFinish();
}
