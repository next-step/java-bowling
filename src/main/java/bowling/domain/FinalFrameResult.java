package bowling.domain;

import java.util.List;

import bowling.engine.Bonus;
import bowling.engine.BonusScores;
import bowling.engine.Result;
import bowling.engine.Shot;
import bowling.engine.Shots;

import static bowling.domain.ShotResult.STRIKE;

public class FinalFrameResult extends FrameResult {
    private FinalFrameResult(Shots shots, Bonus bonus) {
        super(shots, bonus);
    }

    static Result ofFinal(Shots shots, Bonus bonus) {
        if (shots == null || bonus == null) {
            throw new IllegalArgumentException("shots or bonus cannot be null");
        }

        return new FinalFrameResult(shots, bonus);

    }

    static Result ofFinal(Shots shots, List<BonusScores> bonusScoresList) {
        if (shots == null) {
            throw new IllegalArgumentException("shots cannot be null");
        }

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

    @Override
    public Result next(Shot shot) {
        if (shot == null) {
            throw new IllegalArgumentException("the shot cannot be null");
        }

        bonus.applyBonus(shot);

        if (completed()) {
            throw new IllegalStateException("the game is ended");
        }

        return ofFinal(shots.nextShot(shot), bonus.remainBonus());
    }

    @Override
    public boolean hasThirdChance() {
        return shots.isClear() || shots.head() == STRIKE;
    }

    @Override
    public boolean completed() {
        return shots.size() == Shots.NUMBER_OF_FINAL_SHOT || (!hasThirdChance() && super.completed());
    }

    @Override
    public String toString() {
        return "FinalFrameResult{" +
                "shots=" + shots +
                ", bonus=" + bonus +
                '}';
    }
}
