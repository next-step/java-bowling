package bowling.domain;

import bowling.annotations.ForUI;

import java.util.List;

public interface KnockedPinCounts {
    void knockOut(int knockedOutCount);

    boolean isKnockOutPinFinish();

    boolean isStrike();

    boolean isSpare();

    @ForUI
    boolean isFinal();

    boolean isFirstEnd();

    boolean isSecondEnd();

    boolean isBonusEnd();

    @ForUI
    int getFirst();

    @ForUI
    List<KnockedPinCount> getValues();
}
