package bowling.domain.status;

public enum BowlStatus {
    STRIKE, SPARE, MISS, GUTTER, NORAML;

    static BowlStatus valueOf(int turn, int countOfPin) {
        if (turn == 1 && countOfPin == 10) {
            return STRIKE;
        }
        if (turn == 2 && countOfPin == 10) {
            return SPARE;
        }
        if (countOfPin == 0) {
            return GUTTER;
        }
        if (turn == 2 && countOfPin < 10) {
            return MISS;
        }
        return NORAML;
    }


}
