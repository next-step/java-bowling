package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Collections.unmodifiableList;

public class Bowling {
    private static final int MAX_TRIES = 2;
    private static final int MAX_FALLEN_PIN_SUM = 10;

    private final List<FallenPin> fallenPins;

    Bowling(List<FallenPin> fallenPins) {
        this.fallenPins = fallenPins;

        if (totalFallenPins() > MAX_FALLEN_PIN_SUM) {
            throw new IllegalArgumentException(String.format("쓰러진 핀 수의 합은 %s 이하여야 합니다.", MAX_FALLEN_PIN_SUM));
        }
    }

    public static Bowling init() {
        return new Bowling(List.of());
    }

    public Bowling bowl(FallenPin fallenPin) {
        List<FallenPin> result = new ArrayList<>(this.fallenPins);
        result.add(fallenPin);
        return new Bowling(result);
    }

    public boolean isStrike() {
        if (tries() == 1) {
            return fallenPins.get(0).isMax();
        }

        return false;
    }

    public boolean isSpare() {
        if (triesDone()) {
            return totalFallenPins() == MAX_FALLEN_PIN_SUM;
        }

        return false;
    }

    public boolean isFinished() {
        return isStrike() || triesDone();
    }

    public int tries() {
        return fallenPins.size();
    }

    public List<FallenPin> getFallingPins() {
        return unmodifiableList(fallenPins);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bowling)) return false;

        Bowling bowling = (Bowling) o;

        return Objects.equals(fallenPins, bowling.fallenPins);
    }

    @Override
    public int hashCode() {
        return fallenPins != null ? fallenPins.hashCode() : 0;
    }

    private boolean triesDone() {
        return tries() == MAX_TRIES;
    }

    private int totalFallenPins() {
        return fallenPins.stream()
                .mapToInt(FallenPin::getCount)
                .sum();
    }
}
