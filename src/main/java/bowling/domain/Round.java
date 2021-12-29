package bowling.domain;

public class Round {

    private static final int MINIMUM = 1;
    private static final int MAXIMUM = 10;
    private static final int NORMAL_LAST_ROUND = 9;

    private final int round;

    private Round(int round) {
        valid(round);
        this.round = round;
    }

    public static Round first() {
        return new Round(MINIMUM);
    }

    public Round next() {
        return new Round(round + 1);
    }

    public boolean isNextLastRound() {
        return round == NORMAL_LAST_ROUND;
    }

    private void valid(int round) {
        if (round < MINIMUM || round > MAXIMUM) {
            throw new IllegalArgumentException("%d ~ %d 라운드만 이용 가능합니다.");
        }
    }

}
