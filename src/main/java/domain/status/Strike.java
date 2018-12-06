package domain.status;

public class Strike extends Status {

    private static final String STRIKE_STR = "X";
    private static Strike strike = null;

    private Strike() {
    }

    public static Status getInstance() {
        if (strike == null) {
            strike = new Strike();
        }
        return strike;
    }

    @Override
    public String toString() {
        return STRIKE_STR;
    }
}
