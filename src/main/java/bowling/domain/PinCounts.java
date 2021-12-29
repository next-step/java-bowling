package bowling.domain;

import bowling.annotations.ForUI;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PinCounts {
    public static final String WRONG_BOWL_COUNT_MESSAGE = "잘못된 투구 수입니다.";

    private static final int MAX_SIZE = 2;

    private static final int FIRST = 0;
    private static final int SECOND = 1;
    private static final int THIRD = 2;

    private static final String SEPARATE_MARK = "|";

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
        String mark = getFirst();
        for (int index = 1; index < values.size(); index++) {
            mark += SEPARATE_MARK + getPinDisplayOf(index);
        }
        return mark;
    }

    @ForUI
    private String getPinDisplayOf(int index) {
        return values.get(index).display();
    }

    @ForUI
    public String getFirst() {
        return getPinDisplayOf(FIRST);
    }

    @ForUI
    public String getSecond() {
        return getPinDisplayOf(SECOND);
    }

    @ForUI
    public String getThird() {
        return getPinDisplayOf(THIRD);
    }
}
