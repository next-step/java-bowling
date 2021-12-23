package bowling;


import bowling.annotations.ForUI;

public interface Frame {
    Frame next();

    void bowl(int knockedOutCount);

    boolean isEnd();

    boolean isBeforeFinalFrame();

    boolean isFinalFrame();

    @ForUI
    KnockedPinCounts getKnockedPinCounts();
}
