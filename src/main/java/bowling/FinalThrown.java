package bowling;

public class FinalThrown implements Thrown {

    private final Pins firstPins;
    private Pins secondPins;
    private Pins bonusPins;

    public FinalThrown(Pins firstPins) {
        this.firstPins = firstPins;
    }

    public void bonusBowl(int bonusPins) {
        this.bonusPins = Pins.from(bonusPins);
    }

    @Override
    public boolean isStrike() {
        return firstPins.isMax();
    }

    @Override
    public boolean hasTurn() {
        if (bonusPins != null) {
            return false;
        }

        if (secondPins != null && firstPins.getFalledPins() + secondPins.getFalledPins() < 10) {
            return false;
        }

        return true;
    }

    @Override
    public void bowl(int secondPins) {
        if (isStrike()) {
            this.secondPins = Pins.from(secondPins);
        } else {
            this.secondPins = firstPins.totalPins(secondPins);
        }
    }

    @Override
    public int getScore() {
        if (bonusPins != null) {
            return firstPins.getFalledPins() + secondPins.getFalledPins() + bonusPins.getFalledPins();
        }

        if (secondPins != null) {
            return firstPins.getFalledPins() + secondPins.getFalledPins();
        }
        return firstPins.getFalledPins();
    }

    @Override
    public int firstPins() {
        return firstPins.getFalledPins();
    }

    @Override
    public int secondPins() {
        if (secondPins == null) {
            return 0;
        }
        return secondPins.getFalledPins();
    }

    public int bonusPins() {
        if (bonusPins == null) {
            return 0;
        }
        return bonusPins.getFalledPins();
    }
}
