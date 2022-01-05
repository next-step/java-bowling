package bowling.domain.result;

import java.util.List;

import bowling.domain.shot.FrameShots;
import bowling.engine.Bonus;
import bowling.engine.Result;
import bowling.engine.Shot;
import bowling.engine.Shots;

public class NormalFrameResult extends FrameResult {
    protected NormalFrameResult(Shots shots, Bonus bonus) {
        super(shots, bonus);
    }

    @Override
    public Result next(Shot shot) {
        if (shot == null) {
            throw new IllegalArgumentException("the shot cannot be null");
        }

        bonus.applyBonus(shot);

        if (completed()) {
            return of(FrameShots.bySingleShot(shot), bonus.remainBonus());
        }

        return of(shots.nextShot(shot), bonus.remainBonus());
    }

    @Override
    public boolean completed() {
        return shots.size() == Shots.NUMBER_OF_SHOT || shots.isClear();
    }
}
