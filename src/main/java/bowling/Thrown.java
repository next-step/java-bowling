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
        return firstPins.getFalledPins() + secondPins.getFalledPins();
    }
}
