package bowling.domain;

public interface Frame {

    Frame addScore(Pins pins);

    boolean isStrike();

    boolean isSpare();

}
