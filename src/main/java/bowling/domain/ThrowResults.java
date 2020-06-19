package bowling.domain;

import bowling.domain.exceptions.InvalidNumberOfHitPinException;
import bowling.domain.exceptions.TooManyThrowResultsException;

import java.util.*;

public class ThrowResults {
    private static final int MAX_NUMBER_OF_THROW_RESULTS = 2;
    private static final NumberOfHitPin MAX_NUMBER_OF_HIT_PINS = new NumberOfHitPin(10);

    private final List<NumberOfHitPin> numberOfHitPins;

    public ThrowResults(List<NumberOfHitPin> numberOfHitPins) {
        validate(numberOfHitPins);

        this.numberOfHitPins = new ArrayList<>(numberOfHitPins);
    }

    public static ThrowResults of(int firstNumberOfHitPin, int secondNumberOfHitPin) {
        return new ThrowResults(
                Arrays.asList(new NumberOfHitPin(firstNumberOfHitPin), new NumberOfHitPin(secondNumberOfHitPin)));
    }

    public static ThrowResults strike() {
        return new ThrowResults(Collections.singletonList(new NumberOfHitPin(10)));
    }

    private void validate(List<NumberOfHitPin> numberOfHitPins) {
        validateNumberOfThrowResults(numberOfHitPins);
        validateNumberOfHitPins(numberOfHitPins);
    }

    private void validateNumberOfHitPins(List<NumberOfHitPin> numberOfHitPins) {
        if (sumThrowResults(numberOfHitPins).compareTo(MAX_NUMBER_OF_HIT_PINS) > 0) {
            throw new InvalidNumberOfHitPinException("쓰러뜨린 핀의 총합은 0 ~ 10을 벗어날 수 없습니다.");
        }
    }

    private void validateNumberOfThrowResults(List<NumberOfHitPin> numberOfHitPins) {
        if (numberOfHitPins.size() > MAX_NUMBER_OF_THROW_RESULTS) {
            throw new TooManyThrowResultsException("투구 수는 2를 넘을 수 없습니다.");
        }
    }

    private NumberOfHitPin sumThrowResults(List<NumberOfHitPin> numberOfHitPins) {
        return numberOfHitPins.stream()
                .reduce(new NumberOfHitPin(0), NumberOfHitPin::plus);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThrowResults that = (ThrowResults) o;
        return Objects.equals(numberOfHitPins, that.numberOfHitPins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfHitPins);
    }
}
