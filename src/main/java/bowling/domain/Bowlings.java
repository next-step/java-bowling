package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Bowlings {

    public static final int NORMAL_MAX_TOTAL_COUNT = 10;
    public static final int NORMAL_MAX_SIZE = 2;
    public static final int FINAL_MAX_TOTAL_COUNT = 30;
    public static final int FINAL_MAX_SIZE = 3;

    private final int maxTotalCount;
    private final int maxSize;
    private final List<Bowling> values;

    private Bowlings(int maxTotalCount, int size) {
        this.maxTotalCount = maxTotalCount;
        this.maxSize = size;
        this.values = new ArrayList<>(size);
    }

    public static Bowlings initNormal() {
        return new Bowlings(NORMAL_MAX_TOTAL_COUNT, NORMAL_MAX_SIZE);
    }

    public static Bowlings initFianl() {
        return new Bowlings(FINAL_MAX_TOTAL_COUNT, FINAL_MAX_SIZE);
    }

    public Bowling get(int index) {
        return values.get(index);
    }

    public void bowling(int count) {
        if (isNotOverMaxTotalCount(count)) {
            throw new IllegalArgumentException("최대 쓰러뜨린수를 초과했습니다.. " + count);
        }
        values.add(Bowling.of(count));
    }

    private boolean isNotOverMaxTotalCount(int count) {
        return getTotalCount(count) > maxTotalCount;
    }

    private int getTotalCount(int count) {
        return getCurrentTotalCount() + count;
    }

    private int getCurrentTotalCount() {
        return values.stream()
                .mapToInt(Bowling::getCount)
                .reduce(0, Integer::sum);
    }

    public int size() {
        return values.size();
    }

    public boolean isEnd() {
        return values.size() == maxSize || getCurrentTotalCount() == maxTotalCount;
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
