package bowling.domain.state;

public class Strike extends Finished {

    public static final String STRIKE_MESSAGE = "X";

    public Strike() {
    }

    @Override
    public String toString() {
        return STRIKE_MESSAGE;
    }
}
