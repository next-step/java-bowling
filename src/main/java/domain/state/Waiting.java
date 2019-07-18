package domain.state;

import domain.Pins;

import static io.OutputResult.EMPTY_SPACE;

public class Waiting implements State {

    public Waiting() {
    }

    @Override
    public boolean isClosed() {
        return false;
    }

    @Override
    public String toSymbol() {
        return EMPTY_SPACE;
    }
}
