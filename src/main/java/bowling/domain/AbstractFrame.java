package bowling.domain;

public abstract class AbstractFrame implements Frame {
    protected final KnockedPinCounts knockedPinCounts;

    public AbstractFrame(KnockedPinCounts knockedPinCounts) {
        this.knockedPinCounts = knockedPinCounts;
    }

    @Override
    public void bowl(int knockedOutCount) {
        knockedPinCounts.knockOut(knockedOutCount);
    }

    @Override
    public boolean isEnd() {
        return knockedPinCounts.isKnockOutPinFinish();
    }

    @Override
    public KnockedPinCounts getKnockedPinCounts() {
        return knockedPinCounts;
    }
}
