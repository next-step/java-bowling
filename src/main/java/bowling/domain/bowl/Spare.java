package bowling.domain.bowl;

import bowling.domain.bowl.type.BowlTypeDto;
import bowling.domain.pitch.PitchResult;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;
import java.util.List;

public class Spare extends Ended{

    private static final int PITCH_COUNT = 2;
    private static final int MAX_PIN_HIT_COUNT = 10;

    private final Pins firstPin;
    private final Pins secondPin;

    public Spare(Pins firstPin, Pins secondPin) {
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }


    @Override
    public String toString(){
        return "[Spare first: "+firstPin
                +" second: "+secondPin+"]";
    }

    @Override
    public Score score() {
        return Score.spare(firstPin.getCount(), secondPin.getCount());
    }

    @Override
    public Score calculateScore(Score before) {
        Score after = before.addValue(firstPin.getCount());
        if(after.isFinished()){
            return after;
        }
        return after.addValue(secondPin.getCount());
    }

    @Override
    public PitchResult getPitchResult() {
        return PitchResult.spare(firstPin.getCount(), secondPin.getCount());
    }

    public static boolean checkType(BowlTypeDto bowlTypeDto){
        List<Integer> scores = bowlTypeDto.getScores();
        if(scores.size() != PITCH_COUNT){
            return false;
        }
        int first = scores.get(0);
        int second = scores.get(1);
        return first + second == MAX_PIN_HIT_COUNT;
    }

}
