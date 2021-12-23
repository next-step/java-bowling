package bowling;

import java.util.ArrayList;
import java.util.List;

public class NormalKnockedPinCounts extends AbstractKnockedPinCounts {
    public static final String NO_BONUS_MESSAGE = "일반 프레임은 bonus가 없습니다.";

    private static final int MAX_SIZE = 2;

    public NormalKnockedPinCounts() {
        this(new ArrayList<>());
    }

    public NormalKnockedPinCounts(List<KnockedPinCount> knockedPinCounts) {
        super(knockedPinCounts, MAX_SIZE);
    }

    @Override
    public void knockOut(int knockedOutCount) {
        checkValidKnockedPinCounts(knockedOutCount);
        if (isSecondEnd() || isStrike()) {
            throw new IllegalArgumentException(WRONG_BOWL_COUNT_MESSAGE);
        }
        values.add(new KnockedPinCount(knockedOutCount));
    }

    @Override
    public boolean isKnockOutPinFinish() {
        return isSecondEnd() || isStrike();
    }

    @Override
    public boolean isFinal() {
        return false;
    }

    @Override
    public boolean isBonusEnd() {
        throw new IllegalArgumentException(NO_BONUS_MESSAGE);
    }
}
