package bowling.domain;

import java.util.List;

import bowling.engine.Bonus;
import bowling.engine.Frame;
import bowling.engine.Shots;
import bowling.engine.Shot;

import static bowling.domain.ShotResult.STRIKE;

public class FinalFrame extends NormalFrame {
    protected FinalFrame(Shots shots, Bonus bonus) {
        super(FrameSequence.FINAL_FRAME, shots, bonus);
    }

    public static Frame of(Shots shots, Bonus bonus) {
        if (shots == null || bonus == null) {
            throw  new IllegalArgumentException("shots or bonus cannot be null");
        }

        return new FinalFrame(shots, bonus);
    }

    static FinalFrame of(List<Shot> shots) {
        return new FinalFrame(FrameShots.ofFinal(shots), FrameBonus.NONE);
    }

    @Override
    public Frame nextShot(Shot shot) {
        if (shot == null) {
            throw new IllegalArgumentException("the shot cannot be null");
        }

        if (completed()) {
            throw new IllegalStateException("the game is ended");
        }

        bonus.applyBonus(shot);
        return of(shots.nextShot(shot), FrameBonus.of(bonus.remainBonus()));
    }

    @Override
    public boolean completed() {
        return shots.size() == Shots.NUMBER_OF_FINAL_SHOT || (!hasThirdChance() && super.completed());
    }

    @Override
    public boolean hasThirdChance() {
        return shots.isClear() || shots.head() == STRIKE;
    }

    @Override
    public boolean isFinal() {
        return true;
    }
}
