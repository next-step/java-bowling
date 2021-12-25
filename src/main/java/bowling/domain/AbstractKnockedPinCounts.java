package bowling.domain;

import java.util.Collections;
import java.util.List;

public abstract class AbstractKnockedPinCounts implements KnockedPinCounts {
    public static final String WRONG_BOWL_COUNT_MESSAGE = "잘못된 투구 수입니다.";

    protected final List<KnockedPinCount> values;

    protected AbstractKnockedPinCounts(List<KnockedPinCount> knockedPinCounts, int maxSize) {
        this.values = knockedPinCounts;

        if (knockedPinCounts.size() > maxSize) {
            throw new IllegalArgumentException(WRONG_BOWL_COUNT_MESSAGE);
        }
    }

    @Override
    public List<KnockedPinCount> getValues() {
        return Collections.unmodifiableList(values);
    }
}
