package bowling.domain.score;

import bowling.domain.bowl.type.BowlType;
import bowling.domain.exception.RunningBowlException;
import bowling.domain.pitch.PitchResult;

public class Score {

    public static final int CANNOT_CALCULATE_SCORE = -1;
    private static final String INVALID_VALUE_OR_LEFT = "Score의 점숫값과 남은 더하기 개수 값은 양수만 허용합니다.";
    private static final int FINISHED_REMAIN_COUNT = 0;

    private int value;
    private final int left;

    public Score(int value, int remainAddCount) {
        validate(value, remainAddCount);
        this.value = value;
        this.left = remainAddCount;
    }


    void validate(int value, int remainAddCount){
        if(value < 0 || remainAddCount < 0){
            throw new IllegalArgumentException(INVALID_VALUE_OR_LEFT);
        }
    }

    public Score addValue(int value){
        this.value += value;
        return new Score(this.value, this.left -1);
    }

    public boolean isFinished(){
        return left == FINISHED_REMAIN_COUNT;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString(){
        return "value: "+ value
                +" left: "+left;
    }

}
