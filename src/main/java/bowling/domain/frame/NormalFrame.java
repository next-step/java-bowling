package bowling.domain.frame;

import bowling.domain.score.NormalScore;

import java.util.Objects;

public class NormalFrame {

    private static final int LAST_ROUND = 9;

    private final int frame;

    private final NormalScore score;

    private final boolean isSecondTry;

    private NormalFrame(int frame, NormalScore score, boolean isSecondTry) {
        this.frame = frame;
        this.score = score;
        this.isSecondTry = isSecondTry;
    }

    public static NormalFrame start(int score) {
        return of(1, NormalScore.from(score), false);
    }

    protected static NormalFrame of(int frame, NormalScore score, boolean isSecondTry) {
        return new NormalFrame(frame, score, isSecondTry);
    }

    public int score() {
        return score.get();
    }

    public int nextFrame() {

        if (isSecondTry) {
            return frame + 1;
        }

        if (score.isStrike()) {
            return frame + 1;
        }

        return frame;

    }

    public NormalFrame next(int score) {

        if (isSecondTry) {
            return next(frame + 1, NormalScore.from(score), false);
        }

        if (this.score.isStrike()) {
            return next(frame + 1, NormalScore.from(score), false);
        }

        return next(frame, this.score.next(score), true);

    }

    private static NormalFrame next(int frame, NormalScore score, boolean isSecondTry) {
        return new NormalFrame(frame, score, isSecondTry);
    }

    public boolean isLast() {
        if (frame > LAST_ROUND) {
            return true;
        }

        if (frame == LAST_ROUND) {
            return isDone();
        }

        return false;
    }

    public boolean isFrame(int frame) {
        return this.frame == frame;
    }

    private boolean isDone() {
        return score.isStrike() || isSecondTry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame normalFrame = (NormalFrame) o;
        return frame == normalFrame.frame && isSecondTry == normalFrame.isSecondTry && Objects.equals(score, normalFrame.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frame, score, isSecondTry);
    }
}
