package bowling.domain.state;

public interface State {
    State nextPitch(int pins);
    String display();
    boolean isFinished();
}
