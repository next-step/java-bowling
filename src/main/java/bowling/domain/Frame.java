package bowling.domain;

public interface Frame {

    Frame add(Pitch pitch);

    int getPitchSize();

    boolean isFinish();

    boolean isSpare();

    int getFirstScore();

    int getSecondScore();
}
