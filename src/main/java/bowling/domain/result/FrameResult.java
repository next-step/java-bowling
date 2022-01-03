package bowling.domain.result;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import bowling.domain.FrameBonus;
import bowling.domain.shot.FrameShots;
import bowling.engine.Bonus;
import bowling.engine.BonusScores;
import bowling.engine.Result;
import bowling.engine.Score;
import bowling.engine.Shot;
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

    static Result of(List<Shot> shots, List<BonusScores> bonusScoresList) {
        return of(FrameShots.of(shots), bonusScoresList);
    }

    public static Result of(Shot shot) {
        return of(FrameShots.bySingleShot(shot), FrameBonus.NONE);
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

    static Result ofFinal(List<Shot> shots, List<BonusScores> bonusScoresList) {
        return ofFinal(FrameShots.ofFinal(shots), bonusScoresList);
    }

    public static Result ofFinal(Result result) {
        if (result == null) {
            throw new IllegalArgumentException("the result cannot be null");
        }

        if (result instanceof FinalFrameResult) {
            return result;
        }

        return ofFinal(FrameShots.ofFinal(result.collect()), result.bonus());
    }

    public static Result emptyResult() {
        return of(FrameShots.emptyShot(), FrameBonus.NONE);
    }

    @Override
    public Score score() {
        return shots.score(bonus);
    }

    @Override
    public boolean notEmpty() {
        return shots.notEmpty();
    }

    @Override
    public boolean remainBonus() {
        return bonus.remain();
    }

    @Override
    public Stream<Shot> stream() {
        return shots.stream();
    }

    @Override
    public int size() {
        return shots.size();
    }

    @Override
    public Bonus bonus() {
        return bonus;
    }

    @Override
    public List<Shot> collect() {
        return shots.collect();
    }

    @Override
    public String toString() {
        return "FrameResult{" +
                "shots=" + shots +
                ", bonus=" + bonus +
                '}';
    }
}
