package bowling.domain;

public interface Pitching {

    boolean isFinished();

    Pitching pitch(FallenPinNumber fallenPinNumber);
}
