package bowling.domain;

public abstract class Round {

    private static final int FIRST_ROUND = 1;
    private static final int FINAL_ROUND = 10;

    private final int round;

    protected Round(int round) {
        this.round = round;
    }

    public static Round of(int round) {
        if (round == FINAL_ROUND) {
            return FinalRound.of(round);
        }
        return NormalRound.of(round);
    }

    public int round() {
        return round;
    }

    public Round next() {
        if (round == 9) {
            return FinalRound.of(FINAL_ROUND);
        }
        return NormalRound.of(this.round + 1);
    }

    public static Round first() {
        return NormalRound.of(FIRST_ROUND);
    }


}
