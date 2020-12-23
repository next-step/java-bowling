package bowling.domain.state;

public class Strike extends State {

    private static final String SYMBOL = "X";

    @Override
    public String toString() {
        return SYMBOL;
    }
}
