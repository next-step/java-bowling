package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Bowlings {

    private List<Bowling> values = new ArrayList<>();

    public Bowling get(int index) {
        return values.get(index);
    }

    public void createNext(int count) {
        if (isNotOverMaxTotalCount(count)) {
            throw new IllegalArgumentException("최대 쓰러뜨린수를 초과했습니다.. " + count);
        }
        values.add(Bowling.of(count));
    }

    private boolean isNotOverMaxTotalCount(int count) {
        return getTotalCount(count) > getMaxTotalCont();
    }

    abstract int getMaxTotalCont();

    private int getTotalCount(int count) {
        int beforeTotal = values.stream()
                .mapToInt(Bowling::getCount)
                .reduce(0, Integer::sum);

        return beforeTotal + count;
    }

    public int size() {
        return values.size();
    }

    //=============================================================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bowlings bowlings = (Bowlings) o;
        return Objects.equals(values, bowlings.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(values);
    }

    @Override
    public String toString() {
        return "Bowlings{" +
                "values=" + values +
                '}';
    }
}
