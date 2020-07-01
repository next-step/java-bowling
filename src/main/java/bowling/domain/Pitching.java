package bowling.domain;

public interface Pitching {

    boolean isFinished(Frame frame);

    Pitching pitch(FallenPinNumber fallenPinNumber);
}
