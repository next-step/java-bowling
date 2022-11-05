package bowling.domain.frame.state;

public interface State {
    State bowl(int pins);

    boolean isFinish();

    boolean canBonusBowl();
}
