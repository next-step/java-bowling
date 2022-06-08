package bowling.domain.state;

public class Strike extends Finished {

    private static final String SYMBOL = "X";

    @Override
    public boolean hasBonusChance() {
        return true;
    }

    @Override
    public int bowlingCount() {
        return ONE_HIT;
    }

    @Override
    public String description() {
        return SYMBOL;
    }
}
