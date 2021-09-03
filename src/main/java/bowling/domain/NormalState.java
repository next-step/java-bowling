package bowling.domain;

import java.util.Objects;

public class NormalState extends State{

    @Override
    public void bowl(Pins pins) {
        if (Objects.isNull(firstPins)) {
            firstPins = pins;
            return;
        }

        if (!Objects.isNull(firstPins)) {
            secondPins = pins;
        }
    }

    @Override
    public boolean isFinish() {
        if (Objects.isNull(firstPins)) {
            return false;
        }
        if (firstPins.isStrike()) {
            return true;
        }
        if (!Objects.isNull(secondPins)) {
            return true;
        }
        return false;
    }


}
