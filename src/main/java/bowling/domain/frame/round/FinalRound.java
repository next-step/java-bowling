package bowling.domain.frame.round;

public class FinalRound extends Round {

    private FinalRound(int round) {
        super(round);
    }

    public static FinalRound of(int round) {
        if (round != 10) {
            throw new IllegalArgumentException("마지막 라운드는 10라운드여야 합니다.");
        }
        return new FinalRound(round);
    }

    public static FinalRound of() {
        return new FinalRound(10);
    }

}
