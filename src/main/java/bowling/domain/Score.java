package bowling.domain;

public class Score {

    private static final int MIN_HIT_COUNT = 0;
    private static final int MAX_HIT_COUNT = 10;
    private static final int MORE_REMAIN = 1;
    private static final int NO_REMAIN = 0;
    private static final String STRIKE = "X";
    private static final String GUTTER = "-";
    private static final String SPARE = "/";

    private int countOfHit;
    private int countOfRemain;

    public Score(int countOfHit, int countOfRemain) {
        this.countOfHit = countOfHit;
        this.countOfRemain = countOfRemain;
    }

    public String getScore() {
        if (countOfHit == MAX_HIT_COUNT && countOfRemain == MORE_REMAIN) {
            return STRIKE;
        }

        if (countOfHit == MAX_HIT_COUNT && countOfRemain == NO_REMAIN) {
            return SPARE;
        }

        if (countOfHit == MIN_HIT_COUNT) {
            return GUTTER;
        }

        return String.valueOf(countOfHit);
    }
}
