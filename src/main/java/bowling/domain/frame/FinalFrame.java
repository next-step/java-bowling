package bowling.domain.frame;

import bowling.domain.rolling.FinalRollings;
import bowling.domain.rolling.Rollings;
import bowling.domain.score.Score;

import java.util.List;
import java.util.Objects;

public class FinalFrame implements Frame {

    private final FinalRollings finalRollings;

    public FinalFrame(FinalRollings finalRollings) {
        this.finalRollings = finalRollings;
    }

    public FinalFrame(int first) {
        this(FinalRollings.first(first));
    }

    public FinalFrame roll(int fallenPin) {
        if (finalRollings == null) {
            return new FinalFrame(FinalRollings.first(fallenPin));
        }
        return new FinalFrame(finalRollings.roll(fallenPin));
    }

    @Override
    public boolean isEnd() {
        if (finalRollings == null) {
            return false;
        }
        return finalRollings.allRolled();
    }

    @Override
    public Frame next() {
        throw new IllegalStateException();
    }

    @Override
    public int number() {
        return FINAL_NUMBER;
    }

    @Override
    public Rollings rollings() {
        return finalRollings;
    }

    @Override
    public Score score(List<Frame> frames) {
        if (finalRollings == null) {
            return Score.ofNone();
        }
        Score score = Score.of(finalRollings);
        if (score.isFixed()) {
            return score;
        }

        return Score.ofNone();
    }

    @Override
    public Score addScore(Score before, List<Frame> frames) {
        Score score = before.plus(finalRollings.first().fallenPin());
        if (score.isFixed()) {
            return score;
        }
        if (finalRollings.second() == null) {
            return score;
        }

        return score.plus(finalRollings.second().fallenPin());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalFrame that = (FinalFrame) o;
        return Objects.equals(finalRollings, that.finalRollings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(finalRollings);
    }

}
