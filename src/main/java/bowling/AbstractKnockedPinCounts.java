package bowling;

import java.util.Collections;
import java.util.List;

public abstract class AbstractKnockedPinCounts implements KnockedPinCounts {
    public static final String WRONG_BOWL_COUNT_MESSAGE = "잘못된 투구 수입니다.";

    protected static final int ZERO = 0;
    protected static final int ONE = 1;
    protected static final int TWO = 2;

    protected final List<KnockedPinCount> values;

    protected AbstractKnockedPinCounts(List<KnockedPinCount> knockedPinCounts, int maxSize) {
        this.values = knockedPinCounts;

        if (knockedPinCounts.size() > maxSize) {
            throw new IllegalArgumentException(WRONG_BOWL_COUNT_MESSAGE);
        }
    }

    protected void checkValidKnockedPinCounts(int count) {
        sum(count);
    }

    private KnockedPinCount sum(int count) {
        return values.stream()
                .reduce(new KnockedPinCount(count), (previous, current) -> previous.sum(current));
    }

    @Override
    public boolean isStrike() {
        return isFirstEnd() && values.get(ZERO).equals(KnockedPinCount.STRIKE_COUNT);
    }

    @Override
    public boolean isSpare() {
        return isSecondEnd() && sum(ZERO).equals(KnockedPinCount.STRIKE_COUNT);
    }

    @Override
    public boolean isFirstEnd() {
        return values.size() == ONE;
    }

    @Override
    public boolean isSecondEnd() {
        return values.size() == TWO;
    }

    @Override
    public int getFirst() {
        return values.get(ZERO).value();
    }

    @Override
    public List<KnockedPinCount> getValues() {
        return Collections.unmodifiableList(values);
    }
}
