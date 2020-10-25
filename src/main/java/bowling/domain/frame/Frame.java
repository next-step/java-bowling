package bowling.domain.frame;

public interface Frame {
    boolean isFinished();

    Frame bowl(int fallenPinCount);

    String print();
}
