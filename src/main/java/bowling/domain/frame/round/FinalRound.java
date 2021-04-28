package bowling.domain.frame.round;

public class FinalRound extends Round {

    private static final int FINAL_ROUND = 10;

    private FinalRound(int round) {
        super(round);
    }

    public static FinalRound of(int round) {
        if (round != FINAL_ROUND) {
            throw new IllegalArgumentException("마지막 라운드는 10라운드여야 합니다.");
        }
        return new FinalRound(round);
    }

    public static FinalRound of() {
        return new FinalRound(10);
    }

}
