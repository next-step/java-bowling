package bowling.domain;

import java.util.List;

import bowling.engine.Shot;
import bowling.engine.Shots;
import bowling.engine.ShotsBuilder;

import static bowling.domain.ShotResult.GUTTER;
import static bowling.engine.Shots.NUMBER_OF_PINS;
import static bowling.engine.Shots.NUMBER_OF_SHOT;

public class FrameShotsBuilder implements ShotsBuilder {
    protected final List<Shot> shots;

    protected FrameShotsBuilder(List<Shot> shots) {
        this.shots = shots;
    }

    public static ShotsBuilder of(List<Shot> shots) {
        if (shots == null) {
            throw new IllegalArgumentException("shots cannot be null");
        }

        return new FrameShotsBuilder(shots);
    }

    @Override
    public Shots build() {
        validate();
        return FrameShots.byBuilder(shots);
    }

    public boolean validate() {
        if (shots.size() > NUMBER_OF_SHOT || sum() > NUMBER_OF_PINS) {
            throw new IllegalArgumentException("invalid score: " + shots);
        }

        return true;
    }

    private int sum() {
        return shots.stream()
                .reduce(GUTTER, Shot::add)
                .toInt();
    }
}
