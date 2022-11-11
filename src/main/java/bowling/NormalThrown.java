package bowling;

public class NormalThrown implements Thrown {

    private final Pins firstPins;
    private Pins secondPins;

    public NormalThrown(Pins firstPins) {
        this.firstPins = firstPins;
    }

    @Override
    public boolean isStrike() {
        return firstPins.isMax();
    }

    @Override
    public boolean hasTurn() {
        return firstPins.isMax() || secondPins != null;
    }

    public boolean isSpare() {
        return secondPins != null && secondPins.isMax();
    }

    @Override
    public void bowl(int secondPins) {
        this.secondPins = firstPins.addPins(secondPins);
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
