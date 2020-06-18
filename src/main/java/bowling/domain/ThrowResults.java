package bowling.domain;

import bowling.domain.exceptions.InvalidNumberOfHitPinException;
import bowling.domain.exceptions.TooManyThrowResultsException;

import java.util.*;

public class ThrowResults {
    private static final int MAX_NUMBER_OF_THROW_RESULTS = 2;
    private static final ThrowResult MAX_NUMBER_OF_HIT_PINS = new ThrowResult(10);

    private final List<ThrowResult> throwResults;

    public ThrowResults(List<ThrowResult> throwResults) {
        validate(throwResults);

        this.throwResults = new ArrayList<>(throwResults);
    }

    public static ThrowResults of(int firstNumberOfHitPin, int secondNumberOfHitPin) {
        return new ThrowResults(
                Arrays.asList(new ThrowResult(firstNumberOfHitPin), new ThrowResult(secondNumberOfHitPin)));
    }

    public static ThrowResults strike() {
        return new ThrowResults(Collections.singletonList(new ThrowResult(10)));
    }

    private void validate(List<ThrowResult> throwResults) {
        validateNumberOfThrowResults(throwResults);
        validateNumberOfHitPins(throwResults);
    }

    private void validateNumberOfHitPins(List<ThrowResult> throwResults) {
        if (sumThrowResults(throwResults).compareTo(MAX_NUMBER_OF_HIT_PINS) > 0) {
            throw new InvalidNumberOfHitPinException("쓰러뜨린 핀의 총합은 0 ~ 10을 벗어날 수 없습니다.");
        }
    }

    private void validateNumberOfThrowResults(List<ThrowResult> throwResults) {
        if (throwResults.size() > MAX_NUMBER_OF_THROW_RESULTS) {
            throw new TooManyThrowResultsException("투구 수는 2를 넘을 수 없습니다.");
        }
    }

    private ThrowResult sumThrowResults(List<ThrowResult> throwResults) {
        return throwResults.stream()
                .reduce(new ThrowResult(0), ThrowResult::plus);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThrowResults that = (ThrowResults) o;
        return Objects.equals(throwResults, that.throwResults);
    }

    @Override
    public int hashCode() {
        return Objects.hash(throwResults);
    }
}
