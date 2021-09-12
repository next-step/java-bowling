package step3;

public class Ready implements State {
    public State bowl(int falledPins) {
        if (falledPins == 10) {
            return new Strike();
        }
        return new FirstBowl(falledPins);
    }
}
