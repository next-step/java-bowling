package bowling.domain;

import bowling.domain.state.Ready;
import bowling.domain.state.Spare;
import bowling.domain.state.State;
import bowling.domain.state.Strike;

public class LastFrameSet extends FrameSet {

    private boolean bonusPlay;

    private LastFrameSet(State state, boolean bonusPlay) {
        super(10, state);
        this.bonusPlay = bonusPlay;
    }

    public static FrameSet create() {
        return new LastFrameSet(new Ready(), false);
    }

    public static FrameSet createBonus() {
        return new LastFrameSet(new Ready(), true);
    }

    @Override
    public FrameSet readyNext() {
        if (!bonusPlay && canBonusPlay()) {
            return createBonus();
        }

        return this;
    }

    @Override
    public boolean isEnd() {
        return (!bonusPlay && state.isEnd()) || (bonusPlay && !(state instanceof Ready));
    }

    @Override
    public FrameSet snapShot() {
        return new LastFrameSet(state.snapShot(), bonusPlay);
    }

    private boolean canBonusPlay() {
        return state instanceof Strike || state instanceof Spare;
    }
}
