package bowling.domain.state;

import static bowling.domain.BowlingGame.PIN_COUNT;

public class Ready implements State {
    private static final String READY = "";

    @Override
    public boolean isBowl() {
        return true;
    }

    @Override
    public State bowl(final int fallenPinCount) {
        if (fallenPinCount < 0 || fallenPinCount > PIN_COUNT) {
            throw new IllegalArgumentException(String.format("쓰러뜨린 핀 개수가 잘못 되었습니다. %d", fallenPinCount));
        }

        if (fallenPinCount == PIN_COUNT) {
            return new Strike();
        }
        return new Trying(fallenPinCount);
    }

    @Override
    public String print() {
        return READY;
    }
}
