package bowling.domain.frame;

public interface Frame {
    Frame bowl(Pin pin);

    boolean isFinished();
}
