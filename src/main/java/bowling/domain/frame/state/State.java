package bowling.domain.frame.state;

public interface State {
    State bowl(int pins);

    Score createScore();

    boolean isFinish();

    boolean canBonusBowl();
}
