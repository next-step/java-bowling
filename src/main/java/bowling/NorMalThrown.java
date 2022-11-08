package bowling;

public class NorMalThrown implements Thrown {

    private final Pins firstPins;
    private Pins secondPins;

    public NorMalThrown(Pins firstPins) {
        this.firstPins = firstPins;
    }

    @Override
    public boolean isStrike() {
        return firstPins.isMax();
    }

    @Override
    public boolean hasTurn() {
        if (isStrike()) {
            return false;
        }

        if (secondPins != null) {
            return false;
        }

        return true;
    }

    public boolean isSpare() {
        return secondPins != null && secondPins.isMax();
    }

    @Override
    public void bowl(int secondPins) {
        this.secondPins = firstPins.totalPins(secondPins);
    }

    @Override
    public int getScore() {
        if (secondPins == null) {
            return firstPins.getFalledPins();
        }
        return secondPins.getFalledPins();
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

}
