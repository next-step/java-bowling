package bowling_step3.domain;

import java.util.*;

public class Subtotals {
    private List<Integer> subtotal;

    public Subtotals() {
        this(new LinkedList<>());
    }

    public Subtotals(List<Integer> subtotal) {
        this.subtotal = subtotal;
    }

    public static Player winner(Map<Player, Subtotals> playerSubtotals) {
        return Collections.max(playerSubtotals.entrySet(), (entry1, entry2) -> entry1.getValue().subtotal.get(9) - entry2.getValue().subtotal.get(9))
                .getKey();
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
