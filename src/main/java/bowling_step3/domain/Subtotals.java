package bowling_step3.domain;

import java.util.LinkedList;
import java.util.List;

public class Subtotals {
    private List<Integer> subtotal;
    public Subtotals(List<Integer> subtotal) {
        this.subtotal = subtotal;
    }

    public Subtotals() {
        this(new LinkedList<>());
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
}
