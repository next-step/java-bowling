package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class TotalHittingPins {

    private static final int TOTAL_PINS = 10;
    private static final int FIRST_PITCHING = 0;
    private static final int SECOND_PITCHING = 1;
    private static final int THIRD_PITCHING = 2;
    private static final int MAX_PITCHING = 3;
    private static final int TWO_STRIKE = 2;
    public static final String ERROR_MAX_PINS_SIZE_MSG = "최대 3번 투구 할 수 있습니다.";

    private final List<HittingPins> totalPins;

    public TotalHittingPins() {
        totalPins = new ArrayList<>();
    }

    public TotalHittingPins(List<HittingPins> pins) {
        this.totalPins = new ArrayList<>(pins);
    }

    public void addPins(HittingPins pins) {
        if (size() == MAX_PITCHING) {
            throw new IllegalArgumentException(ERROR_MAX_PINS_SIZE_MSG);
        }
        this.totalPins.add(pins);
    }

    public int size() {
        return this.totalPins.size();
    }

    public int getCountOfHits(int pitchingNumber) {
        return this.totalPins.get(pitchingNumber).getCount();
    }

    public boolean isSpare() {
        return this.totalPins.stream()
                .mapToInt(HittingPins::getCount)
                .sum() == TOTAL_PINS;
    }

    public int getFirstPins() {
        return this.totalPins.get(FIRST_PITCHING).getCount();
    }

    public int getSecondPins() {
        return this.totalPins.get(SECOND_PITCHING).getCount();
    }

    public int getThirdPins() {
        return this.totalPins.get(THIRD_PITCHING).getCount();

    }

    public boolean isTwoStrike() {
        return totalPins.stream()
                .filter(HittingPins::isStrike)
                .count() == TWO_STRIKE;
    }

    public List<HittingPins> getTotalPins() {
        return Collections.unmodifiableList(totalPins);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TotalHittingPins that = (TotalHittingPins) o;
        return Objects.equals(totalPins, that.totalPins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalPins);
    }

    @Override
    public String toString() {
        return "TotalHittingPins{" +
                "totalPins=" + totalPins +
                '}';
    }

}
