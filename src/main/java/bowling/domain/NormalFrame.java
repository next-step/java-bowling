package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class NormalFrame {

    private static final int MAX_PINS_COUNT = 10;

    private final int number;
    private final List<PitchResult> results;

    public NormalFrame(int number) {
        this.number = number;
        this.results = new ArrayList<>();
    }

    public void bowl(int fallenPins) {
        if (isEnd()) {
            throw new IllegalArgumentException();
        }

        if (isTotalPinsMoreThan10With(fallenPins)) {
            throw new IllegalArgumentException();
        }

        if (isSpare(fallenPins)) {
            results.add(PitchResult.spare(fallenPins));
            return;
        }

        results.add(PitchResult.of(fallenPins));
    }

    private boolean isTotalPinsMoreThan10With(int fallenPins) {
        return totalPins() + fallenPins > MAX_PINS_COUNT;
    }

    public boolean isEnd() {
        return hasStrike() || results.size() == 2;
    }

    private boolean isSpare(int fallenPins) {
        return !results.isEmpty() && (totalPins() + fallenPins == MAX_PINS_COUNT);
    }

    private boolean hasStrike() {
        return results.contains(PitchResult.of(MAX_PINS_COUNT));
    }

    private int totalPins() {
        return results.stream()
                .mapToInt(PitchResult::fallenPins)
                .sum();
    }

    public List<PitchResult> results() {
        return Collections.unmodifiableList(results);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return number == that.number && Objects.equals(results, that.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, results);
    }

}
