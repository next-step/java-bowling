package bowling.domain.state;

import bowling.domain.KnockedPinCount;
import bowling.domain.PinCounts;

import java.util.Collections;
import java.util.List;

public abstract class AbstractFinished implements State, Finished {
    protected static final String STRIKE_MARK = "X";
    protected static final String SPARE_MARK = "/";
    protected static final String SEPARATE_MARK = "|";
    protected static final int ZERO = 0;
    protected static final int ONE = 1;

    protected final PinCounts pinCounts;

    protected AbstractFinished(int... pinCounts) {
        this(new PinCounts(pinCounts));
    }

    protected AbstractFinished(KnockedPinCount ... knockedPinCounts) {
        this(new PinCounts(knockedPinCounts));
    }

    public AbstractFinished(PinCounts pinCounts) {
        this.pinCounts = pinCounts;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public List<KnockedPinCount> getValues() {
        return Collections.unmodifiableList(pinCounts.getValues());
    }
}
