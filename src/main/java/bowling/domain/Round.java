package bowling.domain;

public class Round {

    private final int round;

    public Round(int round) {
        this.round = round;
    }

    public static Round from(int round) {
        return new Round(round);
    }

    public static Round firstRound() {
        return new Round(0);
    }

    public static Round finalRound() {
        return new Round(1);
    }
}
