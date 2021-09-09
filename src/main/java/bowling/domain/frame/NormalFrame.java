package bowling.domain.frame;

import bowling.domain.frame.rolling.NormalRollings;
import bowling.domain.frame.rolling.Rollings;

import java.util.List;
import java.util.Objects;

public class NormalFrame implements Frame {

    private final int number;
    private final NormalRollings normalRollings;

    public NormalFrame(NormalRollings normalRollings, int number) {
        this.normalRollings = normalRollings;
        this.number = number;
    }

    public NormalFrame(NormalRollings normalRollings) {
        this.normalRollings = normalRollings;
        this.number = INIT_NUMBER;
    }

    public NormalFrame(int first) {
        this(NormalRollings.first(first), INIT_NUMBER);
    }

    public NormalFrame roll(int fallenPin) {
        if (this.normalRollings == null) {
            return new NormalFrame(NormalRollings.first(fallenPin), this.number);
        }
        return new NormalFrame(this.normalRollings.second(fallenPin), this.number);
    }

    @Override
    public Frame next() {
        if (!normalRollings.allRolled()) {
            throw new CannotNextFrameException();
        }
        if (isLastNormalFrame()) {
            return new FinalFrame(null);
        }
        return new NormalFrame(null, this.number + 1);
    }

    private boolean isLastNormalFrame() {
        return this.number == LAST_NORMAL_NUMBER;
    }

    @Override
    public boolean isEnd() {
        return normalRollings.allRolled();
    }

    @Override
    public int number() {
        return number;
    }

    @Override
    public Rollings rollings() {
        return normalRollings;
    }

    @Override
    public Score score(List<Frame> frames) {
        if (normalRollings == null) {
            return Score.ofNone();
        }
        Score score = Score.of(normalRollings);
        if (score.isFixed()) {
            return score;
        }

        return nextScore(score, frames);
    }

    @Override
    public Score addScore(Score before, List<Frame> frames) {
        Score score = before.plus(normalRollings.first().fallenPin());

        if (score.isFixed()) {
            return score;
        }

        if (normalRollings.second() == null) {
            return nextScore(score, frames);
        }

        return score.plus(normalRollings.second().fallenPin());
    }

    private Score nextScore(Score score, List<Frame> frames) {
        Frame nextFrame = nextFrame(frames, number);
        if (nextFrame == null) {
            return score;
        }

        return nextFrame.addScore(score, frames);
    }

    private Frame nextFrame(List<Frame> frames, int number) {
        return frames.stream().filter(frame -> frame.number() == number + 1)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return Objects.equals(normalRollings, that.normalRollings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(normalRollings);
    }
}
