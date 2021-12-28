package bowling.domain;

import bowling.annotations.ForUI;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PinCounts {
    public static final String WRONG_BOWL_COUNT_MESSAGE = "잘못된 투구 수입니다.";

    private static final int MAX_SIZE = 2;

    private static final int FIRST = 0;
    private static final int THIRD = 2;

    private static final int TWO = 2;
    private static final int THREE = 3;

    private static final String SEPARATE_MARK = "|";
    private static final String SPARE_MARK = "/";

    private final List<KnockedPinCount> values;

    private PinCounts(List<KnockedPinCount> knockedPinCounts, int maxSize) {
        this.values = knockedPinCounts;

        if (knockedPinCounts.size() > maxSize) {
            throw new IllegalArgumentException(WRONG_BOWL_COUNT_MESSAGE);
        }
    }

    public PinCounts(int... pinCounts) {
        this(Arrays.stream(pinCounts)
                .mapToObj(KnockedPinCount::new)
                .collect(Collectors.toList()));
    }

    public PinCounts(KnockedPinCount... knockedPinCounts) {
        this.values = Arrays.stream(knockedPinCounts)
                .collect(Collectors.toList());
    }

    public PinCounts(List<KnockedPinCount> knockedPinCounts) {
        this(knockedPinCounts, MAX_SIZE);
    }

    public PinCounts knockOut(int knockedOutCount) {
        values.add(new KnockedPinCount(knockedOutCount));
        return this;
    }

    public List<KnockedPinCount> getValues() {
        return Collections.unmodifiableList(values);
    }

    @ForUI
    public String display() {
        if (isFinalSpare()) {
            return getFirst().display() + SEPARATE_MARK + SPARE_MARK + SEPARATE_MARK + getPinCountOf(THIRD).display();
        }
        if (isSpare()) {
            return getFirst().display() + SEPARATE_MARK + SPARE_MARK;
        }
        return makeMark();
    }

    @ForUI
    private String makeMark() {
        String mark = getFirst().display();
        for (int index = 1; index < values.size(); index++) {
            mark += SEPARATE_MARK + getPinCountOf(index).display();
        }
        return mark;
    }

    @ForUI
    private boolean isSpare() {
        return values.size() == TWO && isSpareCount();
    }

    @ForUI
    private boolean isFinalSpare() {
        return values.size() == THREE && isSpareCount();
    }

    private boolean isSpareCount() {
        return !getFirst().equals(KnockedPinCount.TEN_COUNT)
                && sumToSecond().equals(KnockedPinCount.TEN_COUNT);
    }

    @ForUI
    private KnockedPinCount sumToSecond() {
        return IntStream.range(FIRST, THIRD)
                .mapToObj(values::get)
                .reduce(KnockedPinCount.ZERO_COUNT, KnockedPinCount::sum);
    }

    @ForUI
    private KnockedPinCount getPinCountOf(int index) {
        return values.get(index);
    }

    @ForUI
    private KnockedPinCount getFirst() {
        return getPinCountOf(FIRST);
    }
}
