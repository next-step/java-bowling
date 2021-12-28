package bowling.domain;

public interface Flame {

    Flame addScore(Pins pins);

    boolean isStrike();

    boolean isSpare();

}
