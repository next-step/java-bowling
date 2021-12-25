package bowling.domain;

import bowling.annotations.ForUI;

import java.util.List;

public interface KnockedPinCounts {
    KnockedPinCounts knockOut(int knockedOutCount);

    @ForUI
    List<KnockedPinCount> getValues();
}
