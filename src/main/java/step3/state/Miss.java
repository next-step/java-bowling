package step3.state;

public class Miss extends Finished {
    private int firstPins;
    private int secondPins;

    public Miss(int firstPins, int secondPins) {
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }
}
