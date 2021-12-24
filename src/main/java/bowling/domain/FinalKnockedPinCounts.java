package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class FinalKnockedPinCounts extends AbstractKnockedPinCounts {
    private static final int MAX_SIZE = 3;

    public FinalKnockedPinCounts() {
        this(new ArrayList<>());
    }

    public FinalKnockedPinCounts(List<KnockedPinCount> knockedPinCounts) {
        super(knockedPinCounts, MAX_SIZE);
    }

    @Override
    public void knockOut(int knockedOutCount) {
        if (isFirstBowl()) {
            values.add(new KnockedPinCount(knockedOutCount));
            return;
        }

        if (isFirstEnd() && !isStrike()) {
            checkValidKnockedPinCounts(knockedOutCount);
            values.add(new KnockedPinCount(knockedOutCount));
            return;
        }

        if (isStrike() || isDouble() || (isSecondEnd() && isSpare())) {
            values.add(new KnockedPinCount(knockedOutCount));
            return;
        }
        throw new IllegalArgumentException(WRONG_BOWL_COUNT_MESSAGE);
    }

    @Override
    public boolean isKnockOutPinFinish() {
        return isBonusEnd() || (!isDouble() && !isSpare() && isSecondEnd());
    }

    private boolean isFirstBowl() {
        return values.size() == ZERO;
    }

    private boolean isDouble() {
        return values.stream()
                .filter(KnockedPinCount.STRIKE_COUNT::equals)
                .count() == TWO;
    }

    @Override
    public boolean isFinal() {
        return true;
    }

    @Override
    public boolean isBonusEnd() {
        return values.size() == MAX_SIZE;
    }
}
