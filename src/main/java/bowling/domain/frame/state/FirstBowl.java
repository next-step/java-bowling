package bowling.domain.frame.state;

import java.util.List;

import bowling.domain.frame.Score;

public class FirstBowl extends Running {
    private static final String ILLEGAL_PIN_COUNT_EXCEPTION_MESSAGE = "쓰러트린 핀의 합계는 10을 넘을 수 없습니다.";

    private final Pins firstPins;

    public FirstBowl(Pins firstPins) {
        this.firstPins = firstPins;
    }

    @Override
    public State bowl(int pins) {
        Pins secondPins = new Pins(pins);
        validate(secondPins);

        if (firstPins.isSpare(secondPins)) {
            return new Spare(firstPins, secondPins);
        }

        return new Miss(firstPins, secondPins);
    }

    @Override
    public Score createScore() {
        return new Score(List.of(firstPins.getPins()));
    }

    @Override
    public boolean canBonusBowl() {
        return true;
    }

    private void validate(Pins secondPins) {
        if(!firstPins.isLegalPins(secondPins)) {
            throw new IllegalArgumentException(ILLEGAL_PIN_COUNT_EXCEPTION_MESSAGE);
        }
    }
}
