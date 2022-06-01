package bowling.domain.bowl;

import bowling.domain.pitch.PitchResult;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;
import bowling.domain.score.Scores;

public class Spare extends Ended{

    public static final int PITCH_COUNT = 2;
    public static final int MAX_PIN_HIT_COUNT = 10;
    private static final int SPARE_REMAIN_PITCH_COUNT = 1;

    private final Pins firstPin;
    private final Pins secondPin;

    public Spare(Scores scores) {
        validate(scores);
        this.firstPin = new Pins(scores.getFistScore());
        this.secondPin =  new Pins(scores.getSecondScore());
    }

    private void validate(Scores scores) {
        if(scores.getScoreSum() != MAX_PIN_HIT_COUNT){
            throw new IllegalArgumentException("스페어는 두 투구의 합이 10이 되어야합니다.");
        }
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

    @Override
    public Score score() {
        return new Score(MAX_PIN_HIT_COUNT, SPARE_REMAIN_PITCH_COUNT);
    }

    public static boolean checkType(Scores scores){
        if(!scores.checkSize(PITCH_COUNT)){
            return false;
        }
        return scores.getScoreSum() == MAX_PIN_HIT_COUNT;
    }

    @Override
    public String toString(){
        return "[Spare first: "+firstPin
                +" second: "+secondPin+"]";
    }

}
