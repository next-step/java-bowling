package bowling.domain.score;

public class Score {

    public static final int CANNOT_CALCULATE_SCORE = -1;

    private static final String INVALID_VALUE_OR_LEFT = "Score의 점숫값과 남은 더하기 개수 값은 양수만 허용합니다.";
    private static final int STRIKE_SCORE_VALUE = 10;
    private static final int GUTTER_SCORE_VALUE = 0;

    private static final int STRIKE_BONUS_PITCH_COUNT = 2;
    private static final int SPARE_BONUS_PITCH_COUNT = 1;
    private static final int DEFAULT_BONUS_PITCH_COUNT = 0;

    private static final int FINISHED_REMAIN_COUNT = 0;

    private int value;
    private int left;

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


    public static Score spare(int firstCount, int secondCount) {
        return new Score(firstCount+secondCount, SPARE_BONUS_PITCH_COUNT);
    }

    public static Score strike() {
        return new Score(STRIKE_SCORE_VALUE, STRIKE_BONUS_PITCH_COUNT);
    }

    public static Score miss(int firstCount, int secondCount) {
        return new Score(firstCount + secondCount, DEFAULT_BONUS_PITCH_COUNT);
    }

    public static Score gutter() {
        return new Score(GUTTER_SCORE_VALUE, DEFAULT_BONUS_PITCH_COUNT);
    }

    public boolean isFinished(){
        return left == FINISHED_REMAIN_COUNT;
    }

    public int getValue() {
        return value;
    }

    public int getLeft() {
        return left;
    }

    @Override
    public String toString(){
        return "value: "+ value
                +" left: "+left;
    }

}
