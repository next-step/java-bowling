package bowling.domain.frame;

import bowling.domain.state.FrameBowlStates;

public class FinalFrame implements Frame {

    private final Pins bonusPins;

    private FinalFrame(Pins pins) {
        this.bonusPins = pins;
    }

    public static FinalFrame newInstance() {
        return new FinalFrame(BonusPins.newInstance());
    }

    @Override
    public void play(int downPin) {
        if (!hasTurn()) {
            throw new IllegalStateException("can not play");
        }
        this.bonusPins.down(downPin);
    }

    @Override
    public boolean hasTurn() {
        return this.bonusPins.hasTurn();
    }

    @Override
    public FrameBowlStates getBowlStates() {
        return this.bonusPins.getBowlStates();
    }
}
