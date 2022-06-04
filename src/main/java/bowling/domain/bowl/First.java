package bowling.domain.bowl;

import bowling.domain.bowl.type.BowlType;
import bowling.domain.exception.OutOfPinCountException;
import bowling.domain.pitch.PitchResult;
import bowling.domain.score.Score;
import bowling.domain.pin.Pins;
import bowling.domain.score.Scores;

import java.util.List;

public class First extends Running {

    public static final int MAX_PIN_HIT_COUNT = 10;
    public static final int PITCH_COUNT = 1;
    private final Pins prePins;

    public First(Scores scores) {
        this.prePins = new Pins(scores.getFistScore());
    }

    @Override
    public Bowl pitch(Pins pins) {
        validate(pins);

        Scores scores = new Scores(List.of(prePins.getCount(), pins.getCount()));
        BowlType type = BowlType.getType(scores);
        return type.create(scores);
    }

    private void validate(Pins pins) {
        if (pins.getCount() + prePins.getCount() > MAX_PIN_HIT_COUNT) {
            throw new OutOfPinCountException();
        }
    }


    @Override
    public Score calculateScore(Score before) {
        return before.addValue(prePins.getCount());
    }

    @Override
    public PitchResult getPitchResult() {
        return PitchResult.first(prePins.getCount());
    }

}
