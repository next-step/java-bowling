package bowling.domain.state;

import bowling.domain.KnockedPinCount;
import bowling.domain.KnockedPinCounts;
import bowling.domain.PinCounts;

import java.util.List;

public abstract class AbstractFinished implements State, Finished {
    protected final KnockedPinCounts knockedPinCounts;

    protected AbstractFinished(KnockedPinCount ... knockedPinCounts) {
        this(new PinCounts(knockedPinCounts));
    }

    public AbstractFinished(KnockedPinCounts knockedPinCounts) {
        this.knockedPinCounts = knockedPinCounts;
    }

    @Override
    public List<KnockedPinCount> getValues() {
        return knockedPinCounts.getValues();
    }
}
