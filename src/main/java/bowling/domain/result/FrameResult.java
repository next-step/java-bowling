package bowling.domain.result;

import java.util.List;

import bowling.domain.FrameBonus;
import bowling.domain.shot.FrameShots;
import bowling.engine.Bonus;
import bowling.engine.BonusScores;
import bowling.engine.Result;
import bowling.engine.Score;
import bowling.engine.Shots;

public abstract class FrameResult implements Result {
    public static final Result EMPTY_RESULT = of(FrameShots.emptyShot(), FrameBonus.NONE);

    protected final Shots shots;
    protected final Bonus bonus;

    protected FrameResult(Shots shots, Bonus bonus) {
        this.shots = shots;
        this.bonus = bonus;
    }

    public static Result of(Shots shots, Bonus bonus) {
        if (shots == null || bonus == null) {
            throw new IllegalArgumentException("shots or bonus cannot be null");
        }

        return new NormalFrameResult(shots, bonus);
    }

    static Result of(Shots shots, List<BonusScores> bonusScoresList) {
        if (shots == null) {
            throw new IllegalArgumentException("shots cannot be null");
        }

        return of(shots, FrameBonus.of(bonusScoresList, shots.clearBonus()));
    }

    static Result ofFinal(Shots shots, Bonus bonus) {
        if (shots == null || bonus == null) {
            throw new IllegalArgumentException("shots or bonus cannot be null");
        }

        return new FinalFrameResult(shots, bonus);

    }

    static Result ofFinal(Shots shots, List<BonusScores> bonusScoresList) {
        return ofFinal(shots, FrameBonus.of(bonusScoresList));
    }

    public static Result ofFinal(Result result) {
        if (result == null) {
            throw new IllegalArgumentException("the result cannot be null");
        }

        if (result instanceof FinalFrameResult) {
            return result;
        }

        return ofFinal(result.shots(), result.bonus());
    }

    public static Result emptyResult() {
        return of(FrameShots.emptyShot(), FrameBonus.NONE);
    }

    @Override
    public Score score() {
        return shots.score(bonus);
    }

    @Override
    public Shots shots() {
        return shots;
    }

    @Override
    public Bonus bonus() {
        return bonus;
    }

    @Override
    public String toString() {
        return "FrameResult{" +
                "shots=" + shots +
                ", bonus=" + bonus +
                '}';
    }
}
