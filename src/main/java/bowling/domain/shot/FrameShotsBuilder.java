package bowling.domain.shot;

import java.util.List;

import bowling.engine.Shot;
import bowling.engine.Shots;
import bowling.engine.ShotsBuilder;


public abstract class FrameShotsBuilder implements ShotsBuilder {
    protected final List<Shot> shots;

    protected FrameShotsBuilder(List<Shot> shots) {
        this.shots = shots;
    }

    public static ShotsBuilder of(List<Shot> shots) {
        if (shots == null) {
            throw new IllegalArgumentException("shots cannot be null");
        }

        return new NormalFrameShotsBuilder(shots);
    }

    public static ShotsBuilder ofFinal(List<Shot> shots) {
        if (shots == null) {
            throw new IllegalArgumentException("shots cannot be null");
        }

        return new FinalFrameShotsBuilder(shots);
    }

    @Override
    public Shots build() {
        validate();
        return FrameShots.byBuilder(shots);
    }
}
