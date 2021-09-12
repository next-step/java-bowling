package step3;

public class Miss implements State {
    private int firstPins;
    private int secondPins;

    public Miss(int firstPins, int secondPins) {
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }
}
