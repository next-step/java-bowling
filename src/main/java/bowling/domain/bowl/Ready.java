package bowling.domain.bowl;

import bowling.domain.bowl.type.BowlType;
import bowling.domain.exception.OutOfPinCountException;
import bowling.domain.pitch.PitchResult;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;
import bowling.domain.exception.CannotCalculateException;
import bowling.domain.score.Scores;

import java.util.List;

public class Ready extends Running{

    private static final int PITCH_COUNT = 0;
    private static final int MAX_PIN_HIT_COUNT = 10;

    public Ready() {}
    public Ready(Scores scores) {}

    @Override
    public Bowl pitch(Pins pins) {
        validate(pins);

        Scores scores = new Scores(List.of(pins.getCount()));
        BowlType type = BowlType.getType(scores);

        return type.create(scores);
    }

    private void validate(Pins pins) {
        if(pins.getCount()  > MAX_PIN_HIT_COUNT){
            throw new OutOfPinCountException();
        }
    }

    public static boolean checkType(Scores scores){
        return scores.checkSize(PITCH_COUNT);
    }

    @Override
    public Score calculateScore(Score before) {
        throw new CannotCalculateException();
    }

    @Override
    public PitchResult getPitchResult() {
        return PitchResult.ready();
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public String toString(){
        return "[Ready]";
    }
}
