package bowling.domain.state;

import static bowling.domain.BowlingGame.PIN_COUNT;

public class Trying implements State {
    private static final String TRYING = "%s";
    private static final int GUTTER_NUMBER = 0;
    private static final String GUTTER_SYMBOL = "-";
    private final int firstFallenPinCount;

    public Trying(final int firstFallenPinCount) {
        validate(firstFallenPinCount);
        this.firstFallenPinCount = firstFallenPinCount;
    }

    private void validate(final int firstFallenPinCount) {
        if (firstFallenPinCount < 0) {
            throw new IllegalArgumentException(String.format("쓰러뜨린 핀이 음수 값일 수 없습니다. %d", firstFallenPinCount));
        }

        if (firstFallenPinCount >= PIN_COUNT) {
            throw new IllegalArgumentException(String.format("쓰러뜨린 핀이 최대 핀 개수를 초과하였습니다. %d", firstFallenPinCount));
        }
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public State bowl(final int fallenPinCount) {
        if (fallenPinCount < 0 || (fallenPinCount + firstFallenPinCount) > PIN_COUNT) {
            throw new IllegalArgumentException(String.format("쓰러뜨린 핀 개수가 잘못 되었습니다. %d", fallenPinCount));
        }

        if (firstFallenPinCount + fallenPinCount == 10) {
            return new Spare(firstFallenPinCount);
        }
        return new Miss(firstFallenPinCount, fallenPinCount);
    }

    @Override
    public String print() {
        if (firstFallenPinCount == GUTTER_NUMBER) {
            return String.format(TRYING, GUTTER_SYMBOL);
        }
        return String.format(TRYING, firstFallenPinCount);
    }
}
