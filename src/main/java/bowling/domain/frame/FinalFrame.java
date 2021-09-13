package bowling.domain.frame;

import bowling.domain.frame.vo.FinalFrameStatus;
import bowling.domain.score.FinalScore;

import java.util.Objects;

public class FinalFrame {

    private static final int SUM_FIRST_SECOND_TRIAL_LIMIT = 10;

    private static final int FIRST_TRIAL = 1;

    private static final int SECOND_TRIAL = 2;

    private static final int THIRD_TRIAL = 3;

    private final FinalScore score;

    private final int trial;

    private final FinalFrameStatus status;

    public FinalFrame(FinalScore score, int trial, FinalFrameStatus status) {
        this.score = score;
        this.trial = trial;
        this.status = status;
    }

    protected static FinalFrame of(FinalScore score, int trial, boolean isThirdAvailable, boolean isDone) {
        return new FinalFrame(score, trial, FinalFrameStatus.of(isThirdAvailable, isDone));
    }

    protected static FinalFrame of(FinalScore score, int trial, FinalFrameStatus status) {
        return new FinalFrame(score, trial, status);
    }

    public static FinalFrame start(int score) {
        return of(FinalScore.from(score), 1, false, false);
    }


    public boolean isDone() {
        return status.isDone();
    }

    public int score() {
        return score.get();
    }

    public FinalFrame next(int score) {
        if (isFirstTry()) {
            return secondTry(score);
        }

        if (isSecondTry() && status.isThirdAvailable()) {
            return thirdTry(this.score.next(score));
        }

        return stop(this.score, trial);

    }

    private boolean isFirstTry() {
        return trial == FIRST_TRIAL;
    }

    private boolean isSecondTry() {
        return trial == SECOND_TRIAL;
    }

    private FinalFrame secondTry(int score) {

        if (this.score.isStrike()) {
            return secondTry(this.score.next(score), true, false);
        }

        if (this.score.isSpareWhenAdd(score)) {
            return secondTry(this.score.next(score), true, false);
        }

        validateCombinedScores(score);
        return secondTry(this.score.next(score), false, true);
    }

    private static FinalFrame secondTry(FinalScore score, boolean isThirdAvailable, boolean isDone) {
        return new FinalFrame(score, SECOND_TRIAL, FinalFrameStatus.of(isThirdAvailable, isDone));
    }

    private void validateCombinedScores(int score) {
        if (this.score.add(score) > SUM_FIRST_SECOND_TRIAL_LIMIT) {
            throw new IllegalArgumentException("1차시도와 2차시도의 합계는 10점을 넘을 수 없습니다.");
        }
    }

    private static FinalFrame thirdTry(FinalScore score) {
        return new FinalFrame(score, THIRD_TRIAL, FinalFrameStatus.of(false, true));
    }

    private static FinalFrame stop(FinalScore score, int trial) {
        return new FinalFrame(score, trial, FinalFrameStatus.of(false, true));
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
