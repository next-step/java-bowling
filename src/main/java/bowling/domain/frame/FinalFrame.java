package bowling.domain.frame;

import bowling.domain.score.FinalScore;
import bowling.domain.score.Score;

import java.util.Objects;

public class FinalFrame extends BaseFrame {

    private static final int IDX = 10;

    private FinalFrameStatus status;

    private FinalFrame(Score score, int trial, FinalFrameStatus status, Frame prevFrame) {
        super(trial, score, prevFrame);
        this.status = status;
    }

    protected static FinalFrame of(Score score, int trial, boolean isThirdAvailable, boolean isDone, Frame prevFrame) {
        return new FinalFrame(score, trial, FinalFrameStatus.of(isThirdAvailable, isDone), prevFrame);
    }

    protected static FinalFrame of(Score score, int trial, FinalFrameStatus status, Frame prevFrame) {
        return new FinalFrame(score, trial, status, prevFrame);
    }

    public static FinalFrame start(int score) {
        return of(FinalScore.first(score), 1, false, false, null);
    }

    public static FinalFrame start(int score, Frame prevFrame) {
        return of(FinalScore.first(score), 1, false, false, prevFrame);
    }

    @Override
    public int nextIdx() {
        return IDX;
    }

    @Override
    public BaseFrame bowl(int score) {
        if (isNowFirstTry()) {
            return bowlSecondTry(score);
        }
        return bowlThirdTry(score);
    }

    private FinalFrame bowlSecondTry(int score) {
        accumulateScore(score);
        increaseTrial();
        if (isThirdAvailable()) {
            this.status = FinalFrameStatus.of(true, false);
            return this;
        }
        this.status = FinalFrameStatus.of(false, true);
        return this;
    }


    private FinalFrame bowlThirdTry(int score) {
        accumulateScore(score);
        increaseTrial();
        this.status = FinalFrameStatus.of(false, true);
        return this;
    }

    @Override
    public boolean isLast() {
        return !status.isThirdAvailable() && status.isDone();
    }

    @Override
    public boolean tryAll() {
        return isLast();
    }

    @Override
    public BaseFrame next() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalFrame that = (FinalFrame) o;
        return Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status);
    }
}
