package bowling.domain;

public interface Frame {
    Frame execute(final BowlingPins bowlingPins);

    int getFrameNumber();

    boolean isStrike();

    boolean isSpare();

    boolean isFinished();

    boolean isFinalFrame();

    BowlingPins getPinsOf(Round firstRound);
}
