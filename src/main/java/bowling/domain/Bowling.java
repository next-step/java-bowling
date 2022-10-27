package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Collections.unmodifiableList;

public class Bowling {
    private static final int MAX_TRIES = 2;
    private static final int MAX_PINS_SUM = 10;

    private final List<Pins> pinsList;

    Bowling(List<Pins> pinsList) {
        this.pinsList = pinsList;

        if (totalPins() > MAX_PINS_SUM) {
            throw new IllegalArgumentException(String.format("쓰러진 핀 수의 합은 %s 이하여야 합니다.", MAX_PINS_SUM));
        }
    }

    public static Bowling init() {
        return new Bowling(List.of());
    }

    public Bowling bowl(Pins pins) {
        List<Pins> result = new ArrayList<>(this.pinsList);
        result.add(pins);
        return new Bowling(result);
    }

    public boolean isStrike() {
        if (tries() == 1) {
            return pinsList.get(0).isMax();
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
        return pinsList.size();
    }

    public List<Pins> getPinsList() {
        return unmodifiableList(pinsList);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bowling)) return false;

        Bowling bowling = (Bowling) o;

        return Objects.equals(pinsList, bowling.pinsList);
    }

    @Override
    public int hashCode() {
        return pinsList != null ? pinsList.hashCode() : 0;
    }

    private boolean triesDone() {
        return tries() == MAX_TRIES;
    }

    private int totalPins() {
        return pinsList.stream()
                .mapToInt(Pins::getFallenPins)
                .sum();
    }
}
