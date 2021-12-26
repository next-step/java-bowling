package bowling.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PinCounts implements KnockedPinCounts {
    public static final String WRONG_BOWL_COUNT_MESSAGE = "잘못된 투구 수입니다.";

    private final List<KnockedPinCount> values;

    public PinCounts(List<KnockedPinCount> knockedPinCounts, int maxSize) {
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

    @Override
    public KnockedPinCounts knockOut(int knockedOutCount) {
        values.add(new KnockedPinCount(knockedOutCount));
        return this;
    }

    @Override
    public List<KnockedPinCount> getValues() {
        return Collections.unmodifiableList(values);
    }
    private static final int MAX_SIZE = 2;
}
