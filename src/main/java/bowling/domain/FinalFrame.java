package bowling.domain;

import java.util.Collections;
import java.util.List;

import bowling.engine.Frame;
import bowling.engine.Shots;
import bowling.engine.Shot;

import static bowling.domain.ShotResult.STRIKE;

public class FinalFrame extends NormalFrame {
    // todo bonus from 9 frame
    protected FinalFrame(Shots shots) {
        super(FrameSequence.FINAL_FRAME, shots, Collections.emptyList());
    }

    static FinalFrame of(Shots shots) {
        if (shots == null) {
            throw  new IllegalArgumentException("shots cannot be null");
        }

        return new FinalFrame(shots);
    }

    static FinalFrame of(List<Shot> shots) {
        return new FinalFrame(FrameShots.ofFinal(shots));
    }

    @Override
    public Frame nextShot(Shot shot) {
        if (completed()) {
            throw new IllegalStateException("the game is ended");
        }

        return FinalFrame.of(shots.nextShot(shot));
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
    public boolean complectedBonus() {
        return true;
    }

    @Override
    public boolean isFinal() {
        return true;
    }
}
