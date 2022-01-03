package bowling.domain.result;

import bowling.engine.Bonus;
import bowling.engine.Result;
import bowling.engine.Shot;
import bowling.engine.Shots;

import static bowling.domain.shot.ShotResult.STRIKE;
import static bowling.engine.Shots.NUMBER_OF_SHOT;

public class FinalFrameResult extends FrameResult {
    protected FinalFrameResult(Shots shots, Bonus bonus) {
        super(shots, bonus);
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
        return shots.head() == STRIKE || shots.isClear();
    }

    @Override
    public boolean completed() {
        return shots.size() == Shots.NUMBER_OF_FINAL_SHOT || (shots.size() == NUMBER_OF_SHOT && !hasThirdChance());
    }
}
