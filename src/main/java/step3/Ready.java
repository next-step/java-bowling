package step3;

public class Ready implements State {

    public State bowl(int fallenPins) {
        if (fallenPins == 10) {
            return new Strike();
        }
        return new FirstBowl(fallenPins);
    }
}
