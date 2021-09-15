package bowling.domain.frame;

import bowling.domain.score.FinalScore;

import java.util.Objects;

public class FinalFrame extends Frame {

    private FinalScore score;

    private final FinalFrameStatus status;

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


    public static FinalFrame init() {
        return of(FinalScore.start(), 1, false, false);
    }

    public FinalScore getScore() {
        return score;
    }

    public FinalFrame tryFirst(int score) {
        return of(FinalScore.first(score), 1, FinalFrameStatus.of(false, false));
    }

    public FinalFrame trySecond(int score) {
        this.score = this.score.second(score);
        if (isThirdAvailable()) {
            return of(this.score, 2, FinalFrameStatus.of(true, false));
        }
        return of(this.score, 2, FinalFrameStatus.of(false, true));
    }

    private boolean isThirdAvailable() {
        return this.score.isStrike() || this.score.isSpare();
    }

    public FinalFrame tryThird(int score) {
        return of(this.score.third(score), 3, FinalFrameStatus.of(false, true));
    }

    @Override
    public boolean isLast() {
        return !status.isThirdAvailable() && status.isDone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalFrame that = (FinalFrame) o;
        return trial == that.trial && Objects.equals(score, that.score) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, trial, status);
    }
}
