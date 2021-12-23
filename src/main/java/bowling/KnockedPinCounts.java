package bowling;

import java.util.List;

public interface KnockedPinCounts {
    void knockOut(int knockedOutCount);

    boolean isKnockOutPinFinish();

    boolean isStrike();

    boolean isSpare();

    boolean isFinal();

    boolean isFirstEnd();

    boolean isSecondEnd();

    boolean isBonusEnd();

    int getFirst();

    List<KnockedPinCount> getValues();
}
