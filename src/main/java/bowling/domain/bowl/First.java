package bowling.domain.bowl;

import bowling.domain.bowl.type.BowlType;
import bowling.domain.exception.OutOfPinCountException;
import bowling.domain.pitch.PitchResult;
import bowling.domain.score.Score;
import bowling.domain.pin.Pins;
import bowling.domain.score.Scores;

import java.util.List;

public class First implements Bowl{

    private static final int MAX_PIN_HIT_COUNT = 10;
    private static final int PITCH_COUNT = 1;
    private final Pins prePins;

    public First(Pins prePins) {
        this.prePins = prePins;
    }

    @Override
    public Bowl pitch(Pins pins) {
        validate(pins);

        BowlType type = BowlType.getType(List.of(prePins.getCount(), pins.getCount()));

        if(type.equals(BowlType.SPARE)){
            return new Spare(prePins, pins);
        }
        if(type.equals(BowlType.GUTTER)){
            return new Gutter();
        }
        return new Miss(prePins, pins);
    }

    private void validate(Pins pins) {
        if(pins.getCount() + prePins.getCount() > MAX_PIN_HIT_COUNT){
            throw new OutOfPinCountException();
        }
    }

    public static boolean checkType(Scores scores){
        if(!scores.checkSize(PITCH_COUNT)){
            return false;
        }
        return scores.getFistScore() != MAX_PIN_HIT_COUNT;
    }

    @Override
    public String toString(){
        return "[First first: "+prePins+"]";
    }

    @Override
    public Score calculateScore(Score before) {
        return before.addValue(prePins.getCount());
    }

    @Override
    public PitchResult getPitchResult() {
        return PitchResult.first(prePins.getCount());
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
