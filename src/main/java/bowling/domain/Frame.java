package bowling.domain;


import bowling.annotations.ForUI;

public interface Frame {
    void bowl(int knockedOutCount);

    Frame addNextFrame();

    boolean isEnd();

    boolean isNinthFrame();

    boolean isFinalFrame();

    Frame next();

    @ForUI
    KnockedPinCounts getKnockedPinCounts();
}
