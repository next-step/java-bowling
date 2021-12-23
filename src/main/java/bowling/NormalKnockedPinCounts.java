package bowling;

import java.util.ArrayList;
import java.util.List;

public class NormalKnockedPinCounts {
    public static final String WRONG_BOWL_COUNT_MESSAGE = "잘못된 투구 수입니다.";
    private static final int MAX_SIZE = 2;

    private final List<KnockedPinCount> values;

    public NormalKnockedPinCounts() {
        this(new ArrayList<>());
    }

    public NormalKnockedPinCounts(List<KnockedPinCount> knockedPinCounts) {
        this.values = knockedPinCounts;

        if (knockedPinCounts.size() > MAX_SIZE) {
            throw new IllegalArgumentException(WRONG_BOWL_COUNT_MESSAGE);
        }
    }

    public void knockOut(int count) {
        checkValidKnockedPinCounts(count);
        values.add(new KnockedPinCount(count));
    }

    private void checkValidKnockedPinCounts(int count) {
        sum(count);
    }

    private KnockedPinCount sum(int count) {
        return values.stream()
                .reduce(new KnockedPinCount(count), (previous, current) -> previous.sum(current));
    }
}
