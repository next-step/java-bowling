package bowling.domain.frame;

import bowling.domain.score.FinalScore;

import java.util.List;
import java.util.Objects;

public class FinalFrame extends Frame {

    private static final int IDX = 10;

    private static final int THIRD_TRIAL = 3;

    private FinalScore score;

    private FinalFrameStatus status;

    private FinalFrame(FinalScore score, int trial, FinalFrameStatus status) {
        super(trial);
        this.score = score;
        this.status = status;
    }

    protected static FinalFrame of(FinalScore score, int trial, boolean isThirdAvailable, boolean isDone) {
        return new FinalFrame(score, trial, FinalFrameStatus.of(isThirdAvailable, isDone));
    }

    protected static FinalFrame of(FinalScore score, int trial, FinalFrameStatus status) {
        return new FinalFrame(score, trial, status);
    }

    public static FinalFrame start(int score) {
        return of(FinalScore.first(score), 1, false, false);
    }

    @Override
    public int next() {
        return IDX;
    }

    @Override
    public List<Integer> getAllScores() {
        return score.getAll();
    }

    @Override
    public int calculateScore() {
        return 0;
    }

    @Override
    public Frame tryNext(int score) {
        if (isNowFirstTry()) {
            return trySecond(score);
        }
        return tryThird(score);
    }

    @Override
    public boolean isLast() {
        return !status.isThirdAvailable() && status.isDone();
    }

    @Override
    protected int addWithFirstScore(int score) {
        return this.score.getFirst() + score;
    }

    @Override
    protected int getTotalScore() {
        return 0;
    }

    @Override
    protected Frame getNextFrame() {
        return null;
    }

    private FinalFrame trySecond(int score) {
        this.score = this.score.second(score);
        this.trial = SECOND_TRIAL;
        if (isThirdAvailable()) {
            this.status = FinalFrameStatus.of(true, false);
            return this;
        }
        this.status = FinalFrameStatus.of(false, true);
        return this;
    }

    private boolean isThirdAvailable() {
        return this.score.isStrike() || this.score.isSpare();
    }

    private FinalFrame tryThird(int score) {
        this.score = this.score.third(score);
        this.trial = THIRD_TRIAL;
        this.status = FinalFrameStatus.of(false, true);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalFrame that = (FinalFrame) o;
        return Objects.equals(score, that.score) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, status);
    }
}
