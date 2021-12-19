package bowling.domain.frame;

import bowling.domain.bowl.Bowl;
import bowling.domain.pin.Pin;
import bowling.domain.score.Score;

import java.util.Objects;

public class Frame {

    public static final int MAX_FRAME_NUMBER = 10;
    private static final int MIN_FRAME_NUMBER = 1;
    private static final int NUMBER_UNIT = 1;

    private final int number;
    private final Score score;
    private Bowl bowl;

    public Frame(Bowl bowl) {
        this(MIN_FRAME_NUMBER, bowl);
    }

    public Frame(Score score, Bowl bowl) {
        this(MIN_FRAME_NUMBER, score, bowl);
    }

    public Frame(int number, Bowl bowl) {
        this(number, Score.base(), bowl);
    }

    public Frame(int number, Score score, Bowl bowl) {
        checkRangeOfNumber(number);
        this.number = number;
        this.score = score;
        this.bowl = bowl;
    }

    private void checkRangeOfNumber(int number) {
        if (number < MIN_FRAME_NUMBER || number > MAX_FRAME_NUMBER) {
            throw new IllegalFrameNumberException();
        }
    }

    public static Frame firstOf(Bowl bowl) {
        return new Frame(bowl);
    }

    public Frame nextOf(Bowl bowl) {
        checkFrameIsFinished();
        return new Frame(number + NUMBER_UNIT, score.next(), bowl);
    }

    private void checkFrameIsFinished() {
        if (bowl.canPitch()) {
            throw new UnFinishedFrameException();
        }
    }

    public boolean pitch(Pin pin) {
        bowl = bowl.pitch(pin);
        score.add(bowl.score());
        return bowl.canPitch();
    }

    public void calculateScoreOfPreviousFrame(Score scoreToAdd, Frame previousFrame) {
        Score addedScore = previousFrame.addBonusScore(scoreToAdd);
        score.add(addedScore);
    }

    private Score addBonusScore(Score scoreToAdd) {
        checkFrameIsFinished();
        return score.addBonus(scoreToAdd);
    }

    public Bowl getBowl() {
        return bowl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return number == frame.number && Objects.equals(score, frame.score) && Objects.equals(bowl, frame.bowl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, score, bowl);
    }
}
