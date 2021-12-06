package bowling.domain;

import java.util.List;

import static bowling.domain.PinNumber.MAXIMUM_NUMBER;
import static bowling.domain.PinNumber.MINIMUM_NUMBER;

public class PinNumbers {
    private final int FIRST = 0;
    private final int SECOND = 1;
    private final int NUMBER_OF_TRIAL_FOR_STRIKE = 1;

    private final List<PinNumber> pinNumbers;

    public PinNumbers(List<PinNumber> pinNumbers) {
        this.pinNumbers = pinNumbers;
    }

    public void addPinNumber(PinNumber pinNumber) {
        pinNumbers.add(pinNumber);
    }

    public PinNumber sum() {
        return pinNumbers.stream().reduce(new PinNumber(MINIMUM_NUMBER), PinNumber::add);
    }

    public boolean isStrike() {
        return pinNumbers.size() == NUMBER_OF_TRIAL_FOR_STRIKE && pinNumbers.get(FIRST).equals(new PinNumber(MAXIMUM_NUMBER));
    }

    public boolean isSpare() {
        return pinNumbers.size() != NUMBER_OF_TRIAL_FOR_STRIKE && sum().equals(new PinNumber(MAXIMUM_NUMBER));
    }
}
