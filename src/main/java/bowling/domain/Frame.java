package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Frame {
    public static final int FINAL_MAX_TOTAL_COUNT = 30;
    public static final int FINAL_MAX_SIZE = 3;

    private final int maxTotalCount;
    private final int maxSize;
    private final List<Bowling> values;

    public Frame(int maxTotalCount, int size) {
        this.maxTotalCount = maxTotalCount;
        this.maxSize = size;
        this.values = new ArrayList<>(size);
    }

    public static Frame createNormal() {
        return null;
    }

    public static Frame createFinal() {
        return new Frame(FINAL_MAX_TOTAL_COUNT, FINAL_MAX_SIZE);
    }

    public Bowling get(int index) {
        return values.get(index);
    }

    public int getCount(int index) {
        return values.get(index).getCount();
    }

    public PinCount getLastCount() {
        int count = values.get(values.size() - 1).getCount();
        return PinCount.of(count);
    }

    public void bowling(int count) {
        if (isNotOverMaxTotalCount(count)) {
            throw new IllegalArgumentException("최대 쓰러뜨린수를 초과했습니다.. " + count);
        }

        values.add(Bowling.from(this, count));
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
        return isFrameEnd() || isNotRestPin();
    }

    private boolean isNotRestPin() {
        return getCurrentTotalCount() == maxTotalCount;
    }

    private boolean isFrameEnd() {
        return values.size() == maxSize;
    }

    public Result result(int index) {
        return Result.from(this, index);
    }

    public int sumNowAndBeforePinCount(int index) {
        if (index <= 0) {
            throw new IllegalArgumentException("index는 1 이상만 입력가능 합니다.");
        }

        return get(index).sum(get(index - 1));
    }

    //=============================================================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return Objects.equals(values, frame.values);
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
