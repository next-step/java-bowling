package bowling.domain.state;

public class Strike extends Finished {
    private static final String STRIKE = "X";

    @Override
    public String print() {
        return STRIKE;
    }
}
