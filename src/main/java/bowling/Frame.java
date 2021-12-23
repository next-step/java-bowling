package bowling;


public interface Frame {
    Frame next();

    void bowl(int knockedOutCount);

    boolean isEnd();

    boolean isBeforeFinalFrame();

    boolean isFinalFrame();

    KnockedPinCounts getKnockedPinCounts();
}
