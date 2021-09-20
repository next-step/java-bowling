package bowling.bowlingplayers.domain.frame.status;

import bowling.bowlingplayers.domain.frame.score.*;
import bowling.bowlingplayers.domain.pitching.Pins;
import bowling.bowlingplayers.domain.pitching.Pitching;

public enum Status {
    STRIKE(new StrikeScore()),
    SPARE(new SpareScore()),
    MISS(new MissScore()),
    NULL(new NullScore());

    private final Score score;

    Status(Score score) {
        this.score = score;
    }

    public static Status of(Pitching firstPitching, Pitching secondPitching) {
        if(firstPitching.pins() == Pins.MAXIMUM_PINS) {
            return STRIKE;
        }

        if (secondPitching == null) {
            return NULL;
        }

        if(firstPitching.sum(secondPitching) == Pins.MAXIMUM_PINS) {
            return SPARE;
        }

        return MISS;
    }

    public int score(Pitching firstPitching) {
        return score.score(firstPitching);
    }
}
