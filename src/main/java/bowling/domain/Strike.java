package bowling.domain;

public class Strike extends EndedState {
    private static final String STRIKE_SYMBOL = "X";

    @Override
    public String symbol() {
        return STRIKE_SYMBOL;
    }

    @Override
    public Score score() {
        return new Score(Pitching.MAX_PITCHING, 2);
    }

    @Override
    public Score calculatorScore(Score before) {
        return before.bowl(new Score(Pitching.MAX_PITCHING));
    }
}
