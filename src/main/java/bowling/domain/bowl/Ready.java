package bowling.domain.bowl;

import bowling.domain.bowl.type.BowlTypeDto;
import bowling.domain.bowl.type.BowlType;
import bowling.domain.exception.OutOfPinCountException;
import bowling.domain.pitch.PitchResult;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;
import bowling.domain.exception.CannotCalculateException;
import java.util.List;

public class Ready implements Bowl{

    private static final int PITCH_COUNT = 0;
    private static final int MAX_PIN_HIT_COUNT = 10;

    @Override
    public Bowl pitch(Pins pins) {
        validate(pins);

        BowlTypeDto dto = new BowlTypeDto(List.of(pins.getCount()));
        BowlType type = BowlType.getType(dto);

        if(type.equals(BowlType.STRIKE)){
            return new Strike();
        }

        return new First(pins);
    }

    private void validate(Pins pins) {
        if(pins.getCount()  > MAX_PIN_HIT_COUNT){
            throw new OutOfPinCountException();
        }
    }

    public static boolean checkType(BowlTypeDto bowlTypeDto){
        List<Integer> scores = bowlTypeDto.getScores();
        return scores.size() == PITCH_COUNT;
    }

    @Override
    public String toString(){
        return "[Ready]";
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
    public Score score() {
        return Score.ready();
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
