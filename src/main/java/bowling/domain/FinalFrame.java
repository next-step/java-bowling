package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class FinalFrame implements Frame {

    private final List<Integer> downPins = new ArrayList<>();

    private final Frame normalFrame = NormalFrame.first();


    @Override
    public void play(int downPin) {
        if (this.normalFrame.hasTurn()) {
            this.normalFrame.play(downPin);
            return;
        }

        if (!hasTurn()) {
            throw new IllegalStateException("can not play");
        }

        downPins.add(downPin);
    }

    @Override
    public boolean hasTurn() {
        if (normalFrame.hasTurn()) {
            return true;
        }

        if (this.downPins.size() < getBonusCount()) {
            return true;
        }

        return false;
    }

    @Override
    public FrameBowlStates getBowlStates() {
        FrameBowlStates bowlStates = normalFrame.getBowlStates();
        this.downPins.forEach(
            integer -> bowlStates
                .add(new FrameBowlState(integer, integer == 10 ? ScoreType.STRIKE : ScoreType.MISS))
        );

        return bowlStates;
    }

    private int getBonusCount() {
        FrameBowlStates states = normalFrame.getBowlStates();
        if (states.isStrike()) {
            return 2;
        }
        if (states.isSpare()) {
            return 1;
        }

        return 0;
    }

}
