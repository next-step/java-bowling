package bowling.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PinCounts extends AbstractKnockedPinCounts {
    private static final int MAX_SIZE = 2;

    public PinCounts(KnockedPinCount... knockedPinCounts) {
        this(Arrays.stream(knockedPinCounts)
                .collect(Collectors.toList()));
    }

    public PinCounts(List<KnockedPinCount> knockedPinCounts) {
        super(knockedPinCounts, MAX_SIZE);
    }

    @Override
    public KnockedPinCounts knockOut(int knockedOutCount) {
        values.add(new KnockedPinCount(knockedOutCount));
        return this;
    }
}
