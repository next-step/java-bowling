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

    public boolean isSpare(int secondPins) {
        this.secondPins = firstPins.totalPins(secondPins);
        return this.secondPins.isMax();
    }

    public void bowl(int secondPins) {
        this.secondPins = firstPins.totalPins(secondPins);
    }

    public int getScore() {
        return firstPins.getFalledPins() + secondPins.getFalledPins();
    }
}
