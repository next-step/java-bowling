package bowling.domain.bowl;

import bowling.domain.bowl.type.BowlTypeDto;
import bowling.domain.bowl.type.BowlType;
import bowling.domain.pitch.PitchResult;
import bowling.domain.score.Score;
import bowling.domain.pin.Pins;

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
        BowlTypeDto dto = new BowlTypeDto(List.of(prePins.getCount(), pins.getCount()));
        BowlType type = BowlType.getType(dto);

        if(type.equals(BowlType.SPARE)){
            return new Spare(prePins, pins);
        }
        if(type.equals(BowlType.GUTTER)){
            return new Gutter();
        }
        return new Miss(prePins, pins);
    }

    public static boolean checkType(BowlTypeDto bowlTypeDto){
        List<Integer> scores = bowlTypeDto.getScores();
        if(scores.size() != PITCH_COUNT){
            return false;
        }
        int first = scores.get(0);
        return first != MAX_PIN_HIT_COUNT;
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
    public Score score() {
        return new Score(prePins.getCount(), 1);
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
