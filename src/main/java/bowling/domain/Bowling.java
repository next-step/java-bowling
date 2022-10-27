package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Collections.unmodifiableList;

public class Bowling {
    private static final int MAX_TRIES = 2;
    private static final int MAX_PINS_SUM = 10;

    private final List<FallenPins> fallenPinsList;

    Bowling(List<FallenPins> fallenPinsList) {
        this.fallenPinsList = fallenPinsList;

        if (totalPins() > MAX_PINS_SUM) {
            throw new IllegalArgumentException(String.format("쓰러진 핀 수의 합은 %s 이하여야 합니다.", MAX_PINS_SUM));
        }
    }

    public static Bowling init() {
        return new Bowling(List.of());
    }

    public Bowling bowl(FallenPins fallenPins) {
        List<FallenPins> result = new ArrayList<>(this.fallenPinsList);
        result.add(fallenPins);
        return new Bowling(result);
    }

    public boolean isStrike() {
        if (tries() == 1) {
            return fallenPinsList.get(0).isMax();
        }

        return false;
    }

    public boolean isSpare() {
        if (triesDone()) {
            return totalPins() == MAX_PINS_SUM;
        }

        return false;
    }

    public boolean isFinished() {
        return isStrike() || triesDone();
    }

    public int tries() {
        return fallenPinsList.size();
    }

    public List<FallenPins> getPinsList() {
        return unmodifiableList(fallenPinsList);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bowling)) return false;

        Bowling bowling = (Bowling) o;

        return Objects.equals(fallenPinsList, bowling.fallenPinsList);
    }

    @Override
    public int hashCode() {
        return fallenPinsList != null ? fallenPinsList.hashCode() : 0;
    }

    private boolean triesDone() {
        return tries() == MAX_TRIES;
    }

    private int totalPins() {
        return fallenPinsList.stream()
                .mapToInt(FallenPins::getFallenPins)
                .sum();
    }
}
