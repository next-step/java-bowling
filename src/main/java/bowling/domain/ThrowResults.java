package bowling.domain;

import bowling.domain.exceptions.InvalidNumberOfHitPinException;
import bowling.domain.exceptions.TooManyThrowResultsException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ThrowResults {
    private static final int MAX_NUMBER_OF_THROW_RESULTS = 2;
    private static final int MAX_NUMBER_OF_HIT_PINS = 10;

    private final List<ThrowResult> throwResults;

    public ThrowResults(List<ThrowResult> throwResults) {
        validate(throwResults);

        this.throwResults = new ArrayList<>(throwResults);
    }

    private void validate(List<ThrowResult> throwResults) {
        validateNumberOfThrowResults(throwResults);
        validateNumberOfHitPins(throwResults);
    }

    private void validateNumberOfHitPins(List<ThrowResult> throwResults) {
        if (throwResults.get(0).getNumberOfHitPin() + throwResults.get(1).getNumberOfHitPin() > MAX_NUMBER_OF_HIT_PINS) {
            throw new InvalidNumberOfHitPinException("쓰러뜨린 핀의 총합은 0 ~ 10을 벗어날 수 없습니다.");
        }
    }

    private void validateNumberOfThrowResults(List<ThrowResult> throwResults) {
        if (throwResults.size() > MAX_NUMBER_OF_THROW_RESULTS) {
            throw new TooManyThrowResultsException("투구 수는 2를 넘을 수 없습니다.");
        }
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
