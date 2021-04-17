package bowling.domain;

public class NormalRound extends Round {

    private NormalRound(int round) {
        super(round);
    }

    public static Round of(int position) {
        return new NormalRound(position);
    }
    
}
