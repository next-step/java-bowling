package bowling.domian.state;

public interface State {
    State bowl(int falledPinsCount);

    boolean isFinished();

    boolean canGetScore();
}
