package bowling.domain.state;

import static bowling.domain.ScoreSymbols.EMPTY_SYMBOL;

public class Ready implements State {

    @Override
    public State bowl(int countOfPin) {
        if (countOfPin == 10) {
            return State.ofStrike();
        }

        return State.ofFirstBowl(countOfPin);
    }

    @Override
    public String output() {
        return EMPTY_SYMBOL;
    }

}
