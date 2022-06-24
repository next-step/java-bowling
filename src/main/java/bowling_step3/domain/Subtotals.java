package bowling_step3.domain;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Subtotals {
    private List<Integer> subtotal;

    public Subtotals() {
        this(new LinkedList<>());
    }

    public Subtotals(List<Integer> subtotal) {
        this.subtotal = subtotal;
    }

    public Integer sum() {
        return this.subtotal
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public void add(Integer subtotal) {
        this.subtotal.add(subtotal);
    }

    public Integer last() {
        if (this.subtotal.size() == 0) {
            return 0;
        }
        return this.subtotal.get(this.subtotal.size() - 1);
    }

    public List<Integer> subtotals() {
        return this.subtotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subtotals subtotals = (Subtotals) o;
        return Objects.equals(subtotal, subtotals.subtotal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subtotal);
    }

    @Override
    public String toString() {
        return "Subtotals{" +
                "subtotal=" + subtotal +
                '}';
    }
}
