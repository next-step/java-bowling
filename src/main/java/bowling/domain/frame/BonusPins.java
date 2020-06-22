package bowling.domain.frame;

import bowling.domain.state.FrameBowlState;
import bowling.domain.state.FrameBowlStates;
import bowling.domain.state.ScoreType;
import java.util.ArrayList;
import java.util.List;

public class BonusPins implements Pins {

    private final List<Integer> downPins;
    private final Pins pins;

    private BonusPins(Pins pins) {
         this.pins = pins;
         this.downPins = new ArrayList<>();
    }

    public static Pins newInstance() {
        Pins bonusPins = new BonusPins(new NormalPins());
        return bonusPins;
    }

    @Override
    public void down(int downPin) {
        validate(downPin);

        if (this.pins.hasTurn()) {
            this.pins.down(downPin);
            return;
        }

        this.downPins.add(downPin);
    }

    @Override
    public boolean hasTurn() {
        if (this.pins.hasTurn()) {
            return true;
        }

        if (this.downPins.size() < getBonusCount()) {
            return true;
        }

        return false;
    }

    @Override
    public FrameBowlStates getBowlStates() {
        FrameBowlStates bowlStates = pins.getBowlStates();
        this.downPins.forEach(
            integer -> bowlStates
                .add(new FrameBowlState(integer, integer == 10 ? ScoreType.STRIKE : ScoreType.NUMS))
        );

        return bowlStates;
    }

    private void validate(int downPin) {
        if(downPin < 0 || downPin > 10){
            throw new IllegalArgumentException("invalid downPin");
        }

        if(!hasTurn()){
            throw new IllegalStateException("invalid state");
        }
    }

    private int getBonusCount() {
        FrameBowlStates states = this.pins.getBowlStates();
        if (states.hasStrike()) {
            return 2;
        }
        if (states.hasSpare()) {
            return 1;
        }
        return 0;
    }
}
