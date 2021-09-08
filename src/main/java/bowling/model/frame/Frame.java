package bowling.model.frame;

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

    public abstract FrameNumber nextNumber();

    public abstract boolean isBonusPlay();

    public abstract Score getBonusScore();

    public static Frame first(int score) {
        return new NormalFrame(FrameNumber.first(), FrameScore.first(score));
    }

    protected boolean isStrikeOrSpare() {
        return isStrike() || isSpare();
    }

    protected boolean isStrike() {
        return score.isStrike();
    }

    protected boolean isFirstAndNotStrike() {
        return score.isFirst() && !isStrike();
    }

    protected boolean pitchTwice() {
        return score.pitchTwice();
    }

    private boolean isSpare() {
        return score.isSpare();
    }

    public FrameScore getScore() {
        return score;
    }

    public Score getFirstScore() {
        return score.getFirst();
    }

    public Score getSecondScore() {
        return score.getSecond();
    }

    public boolean isFrameNumberEqual(Frame frame) {
        return number.equals(frame.number);
    }
}
