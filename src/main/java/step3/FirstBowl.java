package step3;

public class FirstBowl implements State {
    private final int pins;

    public FirstBowl(int firstFalledPins) {
        pins = firstFalledPins;
    }

    public State bowl(int secondFalledPins) {
        if (pins + secondFalledPins == 10) {
            return new Spair();
        }
        return new Miss(pins, secondFalledPins);
    }
}
