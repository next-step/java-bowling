package bowling.domain.bowl;

import bowling.domain.pitch.PitchResult;
import bowling.domain.score.Score;
import bowling.domain.pin.Pins;
import bowling.domain.score.Scores;

public class Miss extends Ended{

    public static final int PITCH_COUNT = 2;
    public static final int MIN_PIN_HIT_COUNT = 0;
    public static final int MAX_PIN_HIT_COUNT = 10;
    private static final int DEFAULT_BONUS_PITCH_COUNT = 0;

    private final Pins firstPin;
    private final Pins secondPin;

    public Miss(Scores scores) {
        this.firstPin = new Pins(scores.getFistScore());
        this.secondPin = new Pins(scores.getSecondScore());
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
        return PitchResult.miss(firstPin.getCount(), secondPin.getCount());
    }

    @Override
    public Score score() {
        return new Score(firstPin.getCount() + secondPin.getCount(), DEFAULT_BONUS_PITCH_COUNT);
    }

    public static boolean checkType(Scores scores){
        if(!scores.checkSize(PITCH_COUNT)){
            return false;
        }
        int sum = scores.getScoreSum();
        return MIN_PIN_HIT_COUNT < sum && sum < MAX_PIN_HIT_COUNT;
    }

    @Override
    public String toString(){
        return "[Miss first: "+firstPin
                +" second: "+secondPin+"]";
    }
}
