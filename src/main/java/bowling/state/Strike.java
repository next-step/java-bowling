package bowling.state;

public class Strike implements State {
    private static final String expression = "X";

    @Override
    public String expression() {
        return expression;
    }
}
