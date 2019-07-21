package bowling.model.frame.state;

import bowling.model.DownPin;
import bowling.model.frame.State;

import static bowling.model.DownPin.DOWN_ZERO;

public class Gutter extends FirstState {

    static final String SYMBOL = "-";
    private static final DownPin GUTTER_PIN = DOWN_ZERO;
    private static final Gutter SELF = new Gutter();

    private Gutter() {
        super(GUTTER_PIN);
    }

    static State getInstance() {
        return SELF;
    }

    static boolean isMatch(DownPin downPin) {
        return GUTTER_PIN.equals(downPin);
    }

    @Override
    public Score getScore() {
        return Score.of(GUTTER_PIN);
    }

    @Override
    public String printResult() {
        return SYMBOL;
    }
}
