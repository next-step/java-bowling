package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import static bowling.domain.PinNumber.MAXIMUM_NUMBER;
import static bowling.domain.PinNumber.MINIMUM_NUMBER;

public class PinNumbers {
    private final int FIRST = 0;
    private final int SECOND = 1;
    private final int NUMBER_OF_TRIAL_FOR_STRIKE = 1;
    private final int NUMBER_OF_MAXIMUM_TRIAL = 2;

    private final List<PinNumber> pinNumbers;

    public PinNumbers() {
        this(new ArrayList<>());
    }

    public PinNumbers(List<PinNumber> pinNumbers) {
        this.pinNumbers = pinNumbers;
    }

    public void addPinNumber(PinNumber pinNumber) {
        pinNumbers.add(pinNumber);
    }

    public PinNumber sum() {
        return pinNumbers.stream().reduce(new PinNumber(MINIMUM_NUMBER), PinNumber::add);
    }

    public boolean isContinuable() {
        return pinNumbers.size() < NUMBER_OF_MAXIMUM_TRIAL && !isStrike();
    }

    public boolean isStrike() {
        return pinNumbers.size() == NUMBER_OF_TRIAL_FOR_STRIKE && pinNumbers.get(FIRST).equals(new PinNumber(MAXIMUM_NUMBER));
    }

    public boolean isSpare() {
        return pinNumbers.size() != NUMBER_OF_TRIAL_FOR_STRIKE && sum().equals(new PinNumber(MAXIMUM_NUMBER));
    }

    public Score totalScore() {
        return sum().score();
    }

    public Score firstScore() {
        return pinNumbers.stream()
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("There is no first score"))
                .score();
    }

    public List<Integer> info() {
        List<Integer> pins = new ArrayList<>();
        pinNumbers.forEach(pinNumber -> pins.add(pinNumber.getNumber()));

        return pins;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        PinNumbers that = (PinNumbers) object;

        return Objects.equals(pinNumbers, that.pinNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pinNumbers);
    }
}
