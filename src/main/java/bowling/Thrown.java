package bowling;

public class Thrown {

    private final Pins firstPins;
    private Pins secondPins;

    public Thrown(Pins firstPins) {
        this.firstPins = firstPins;
    }

    public boolean isStrike() {
        return firstPins.isMax();
    }

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
        return secondPins.isMax();
    }

    public void bowl(int secondPins) {
        this.secondPins = firstPins.totalPins(secondPins);
    }

    public int getScore() {
        if (secondPins == null) {
            return firstPins.getFalledPins();
        }
        return secondPins.getFalledPins();
    }

    public int firstPins() {
        return firstPins.getFalledPins();
    }

    public int secondPins() {
        return secondPins.getFalledPins();
    }
}
