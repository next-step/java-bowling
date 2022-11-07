package bowling.domain;

public interface Frame {

    Frame nextFrame();

    boolean canPitch();

    boolean isEmpty();

    int pinNumber(final int index);

    int pinsSize();

    int getNumber();

    void pitch(final int number);

    ScoreType status();
}
