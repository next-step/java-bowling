package bowling.domain;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Frame {

    public static final int LAST_FRAME = 10;
    public static final int FIRST_FRAME = 1;
    public static final int EMPTY_FRAME = 0;

    protected FrameIndex frameIndex;
    protected Score score;
    protected Status status;
    protected Frame prevFrame;

    public Frame(int hitNumberOfPin, int frameIndex) {
        if (hitNumberOfPin == Pin.MAX.getValue()) {
            status = Status.STRIKE;
        }
        score = new Score(hitNumberOfPin);
        this.frameIndex = new FrameIndex(frameIndex);
    }

    public abstract void secondBall(int hitNumberOfPin);

    public int firstScore() {
        return score.firstScore();
    }

    public int secondScore() {
        return score.secondScore();
    }

    public List<String> scores() {
        return score.scores()
                .stream()
                .map(pin -> Integer.toString(pin))
                .collect(Collectors.toList());
    }

    public boolean isSpare() {
        return Status.SPARE.equals(status);
    }

    public boolean isStrike() {
        return Status.STRIKE.equals(status);
    }

    public boolean isMiss() {
        return Status.MISS.equals(status);
    }

    public int frameIndex() {
        return frameIndex.getValue();
    }

    public int nextFrameIndex() {
        return frameIndex.getValue() + 1;
    }

    public TotalScore totalScore() {
        return score.totalScore();
    }

    public abstract int calculateFrameScore();

    protected abstract int cacluateAdditionalScore(TotalScore totalScore);

    public abstract boolean isExistNextFrame();

    protected abstract void validateFrameScore();

    public abstract void calculateFrame();

    public abstract Frame getNextFrame();
}
