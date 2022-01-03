package bowling.domain.shot;

import java.util.List;

import bowling.engine.Shot;

import static bowling.domain.shot.ShotResult.GUTTER;
import static bowling.engine.Shots.NUMBER_OF_PINS;
import static bowling.engine.Shots.NUMBER_OF_SHOT;

public class NormalFrameShotsBuilder extends FrameShotsBuilder {

    protected NormalFrameShotsBuilder(List<Shot> shots) {
        super(shots);
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
