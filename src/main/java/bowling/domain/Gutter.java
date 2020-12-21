package bowling.domain;

public class Gutter implements State {
    private final Pins firstPins;
    private Pins secondPins;

    public Gutter(Pins firstPins, Pins secondPins) {
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    public Gutter(Pins firstPins) {
        this.firstPins = firstPins;
    }

    @Override
    public Score getScore() {
        return new Score();
    }

    @Override
    public String toString() {
        String ret = Symbol.GUTTER.getSymbol();
        if (this.secondPins != null) {
            ret = ret + "|" + Symbol.GUTTER.getSymbol();
        }
        return ret;
    }

    @Override
    public boolean isFinished() {
        return secondPins != null;
    }
}
