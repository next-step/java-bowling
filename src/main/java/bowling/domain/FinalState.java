package bowling.domain;

import java.util.Objects;

public class FinalState extends State {

    private Pins bonusPins;

    @Override
    public void bowl(Pins pins) {
        if (Objects.isNull(firstPins)) {
            firstPins = pins;
            return;
        }

        if (Objects.isNull(secondPins)) {
            secondPins = pins;
            return;
        }

        if (Objects.isNull(bonusPins)) {
            bonusPins = pins;
        }
    }

    @Override
    public boolean isFinish() {
        if (!Objects.isNull(bonusPins)) {
            return true;
        }

        if (Objects.isNull(secondPins)) {
            return false;
        }

        if (secondPins.isSpare(firstPins)) {
            return false;
        }

        if (firstPins.isStrike() && secondPins.isStrike()) {
            return false;
        }

        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalState that = (FinalState) o;
        return Objects.equals(bonusPins, that.bonusPins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bonusPins);
    }
}
