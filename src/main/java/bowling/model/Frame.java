package bowling.model;

public abstract class Frame {
    protected FrameNumber number;
    protected FrameScore score;

    protected Frame(FrameNumber number, FrameScore score) {
        validateNumberRange(number);

        this.number = number;
        this.score = score;
    }

    public Frame(int number, int firstScore, int secondScore) {
        FrameNumber frameNumber = new FrameNumber(number);
        validateNumberRange(frameNumber);

        this.number = new FrameNumber(number);
        this.score = new FrameScore(Score.of(firstScore), Score.of(secondScore));
    }

    protected abstract void validateNumberRange(FrameNumber number);

    public abstract boolean canPlayNext();

    public abstract Frame next(int score);

    public static Frame first(int score) {
        return new NormalFrame(FrameNumber.first(), FrameScore.first(score));
    }

    protected boolean isStrikeOrSpare() {
        return isStrike() || isSpare();
    }

    protected boolean isStrike() {
        return score.isStrike();
    }

    protected boolean pitchTwice() {
        return score.pitchTwice();
    }

    private boolean isSpare() {
        return score.isSpare();
    }
}
