package step2.domain.state;

public class Strike implements State {

    private final static String SYMBOL = "X";

    @Override
    public String toString() {
        return SYMBOL;
    }

}
