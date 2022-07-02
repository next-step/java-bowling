package bowling_step3.domain;

import java.util.*;

public class Subtotals {
    private List<Integer> subtotals;

    public Subtotals() {
        this(new LinkedList<>());
    }

    public Subtotals(List<Integer> subtotals) {
        this.subtotals = subtotals;
    }

    public static Player winner(Map<Player, Subtotals> playerSubtotals) {
        return Collections.max(playerSubtotals.entrySet(), (entry1, entry2) -> entry1.getValue().subtotals.get(9) - entry2.getValue().subtotals.get(9))
                .getKey();
    }

    public void add(Integer subtotal) {
        this.subtotals.add(subtotal);
    }

    public Integer last() {
        if (this.subtotals.size() == 0) {
            return 0;
        }
        return this.subtotals.get(this.subtotals.size() - 1);
    }

    public List<Integer> subtotals() {
        return this.subtotals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subtotals subtotals1 = (Subtotals) o;
        return Objects.equals(subtotals, subtotals1.subtotals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subtotals);
    }

    @Override
    public String toString() {
        return "Subtotals{" +
                "subtotals=" + subtotals +
                '}';
    }
}
