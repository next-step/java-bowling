package bowling.domain;

public class Score {

    public static final int MINIMAL_SWING_SCORE = 0;
    public static final int MAXIMUM_SWING_SCORE = 10;

    public static final int NORMAL_FRAME_SWING_COUNT = 2;
    public static final int FINAL_FRAME_SWING_COUNT = 3;

    protected static final String ERR_SCORE_RANGE = "0부터 10까지의 값만 입력할 수 있습니다.";
    protected static final String ERR_SUM_OF_SCORE_VALUE = "한 프레임 당 투구 점수는 최대 10점입니다.";

    private int maxSwingCount;
    private int score;

    private Score(int maxSwingCount) {
        this.maxSwingCount = maxSwingCount;
        score = 0;
    }

    public static Score normal() {
        return new Score(NORMAL_FRAME_SWING_COUNT);
    }

    public static Score last() {
        return new Score(FINAL_FRAME_SWING_COUNT);
    }

    public void swing(int score) {
        this.score += score;
    }

    public int getValue() {
        return score;
    }
}
