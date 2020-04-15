package bowling.domain.frame.state;

public interface State {

    State bowl(int pinsCount);

    boolean isFinish();
}
