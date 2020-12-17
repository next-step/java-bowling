package bowling.model.frame;

import java.util.Optional;

public class FinalFrame extends Frame {
    public static final int MAX_BONUS_COUNT = 3;

    public FinalFrame() {
        super(FrameNumber.from(FrameNumber.MAX_FRAME_NUMBER));
    }

    @Override
    public Frame bowling(int fallenPins) {
        if (canBonusBowling()) {
            states.changeLastStateToBonus(fallenPins);
            return this;
        }

        states.bowling(fallenPins);
        return this;
    }

    private boolean canBonusBowling(){
        return states.isFinished() && !states.canLastCalculate();
    }

    @Override
    public Optional<Integer> getScore() {
        int totalScore = states.sumScore();
        return isFinished() ? Optional.of(totalScore) : Optional.empty();
    }

    @Override
    public boolean isFinished() {
        return states.isFinished() && states.canLastCalculate() || states.size() == MAX_BONUS_COUNT;
    }
}
