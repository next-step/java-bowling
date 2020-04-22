package bowling.domain;

public enum Sign {

    STRIKE("X"),
    SPARE("/"),
    GUTTER("-"),
    MISS("");

    private static final int FRAME_MAX_SCORE = 10;
    private static final int FINAL_MAX_SCORE = 20;
    private static final int ZERO = 0;
    private static final int SECOND_TRY_NUMBER = 2;
    private static final int FINAL_TRY_NUMBER = 3;
    private final String sign;

    Sign(String sign) {
        this.sign = sign;
    }

    public static Sign matchSign(int score, int numberOfTry, int sum) {
        if (score == ZERO){
            return GUTTER;
        }
        if (numberOfTry == SECOND_TRY_NUMBER && sum == FRAME_MAX_SCORE) {
            return SPARE;
        }
        if (numberOfTry == FINAL_TRY_NUMBER && sum == FINAL_MAX_SCORE && score != FRAME_MAX_SCORE) {
            return SPARE;
        }
        if (score == FRAME_MAX_SCORE) {
            return STRIKE;
        }
        return MISS;
    }

    public String getSign() {
        return sign;
    }
}
