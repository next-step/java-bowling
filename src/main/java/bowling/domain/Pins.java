package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pins {
    public static final int MAX_PINS = 10;
    private int left;
    private List<Integer> falls = new ArrayList<>();

    public Pins(int left, List<Integer> falls) {
        this.left = left;
        this.falls = falls;
    }

    public Pins() {
        left = MAX_PINS;
    }

    public int getLeft() {
        return left;
    }

    public void fall(int count) {
        left -= count;
        if (left < 0) {
            throw new IllegalArgumentException("pins must be over 0");
        }
        falls.add(count);
    }

    public List<Integer> getFalls() {
        return falls;
    }

    public boolean isAllDown() {
        return left == 0;
    }


    public boolean isSpare() {
        return falls.size() == 2 && countAllFalls() == MAX_PINS;
    }

    private int countAllFalls() {
        return falls.stream().mapToInt(Integer::intValue).sum();
    }

    public boolean isStrike() {
        return falls.size() == 1 && countAllFalls() == MAX_PINS;
    }

    public Integer getFirstFall() {
        return falls.get(0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pins pins = (Pins) o;
        return left == pins.left &&
            Objects.equals(falls, pins.falls);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, falls);
    }
}
