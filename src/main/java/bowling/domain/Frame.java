package bowling.domain;

public interface Frame {

    Frame add(Pitch pitch);

    int getPitchSize();

    boolean isFinish();

    boolean isSpare();

    int getFrameScore();

    int getFirstScore();

    int getSecondScore();
}
