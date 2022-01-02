package bowling.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import bowling.engine.Bonus;
import bowling.engine.BonusScores;
import bowling.engine.Result;
import bowling.engine.Score;
import bowling.engine.Shot;
import bowling.engine.Shots;

public class FrameResult implements Result {
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

        return new FrameResult(shots, bonus);
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
        if (shot == null) {
            throw new IllegalArgumentException("the first shot cannot be null");
        }

        return of(List.of(shot), Collections.emptyList());
    }
    public static Result empty() {
        return of(FrameShots.emptyShot(), FrameBonus.NONE);
    }

    @Override
    public Result next(Shot shot) {
        if (shot == null) {
            throw new IllegalArgumentException("the shot cannot be null");
        }

        bonus.applyBonus(shot);

        if (completed()) {
            return of(List.of(shot), bonus.remainBonus());
        }

        return of(shots.nextShot(shot), bonus.remainBonus());
    }

    @Override
    public Score score() {
        return shots.score(bonus);
    }

    @Override
    public boolean completed() {
        return shots.size() == Shots.NUMBER_OF_SHOT || shots.isClear();
    }

    @Override
    public boolean completedBonus() {
        return bonus.completed();
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
    public boolean hasThirdChance() {
        return false;
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
