package bowling.domain.engine.frame;

import bowling.domain.engine.frame.state.State;

public class FinalFrameCount {

    private int count = 2;
    private boolean bonusRemained = true;

    public void changeCount(State lastState) {
        count--;

        if (lastState.canPromoteToBonusState() && bonusRemained) {
            count++;
            bonusRemained = false;
        }
    }

    public boolean isFinished() {
        return count == 0;
    }

}
