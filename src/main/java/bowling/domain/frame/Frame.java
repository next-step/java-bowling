package bowling.domain.frame;

public interface Frame {
    Frame bowl(Pin pin);

    boolean isGameEnd();

    boolean isFinished();

    boolean isEqualsRound(Frame frame);

    Round round();
}
