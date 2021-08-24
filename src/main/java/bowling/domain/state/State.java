package bowling.domain.state;

public interface State {
    State nextPitch(int pins);
    State lastPitch(int pins);
    String display();
    boolean isFinished();
}
