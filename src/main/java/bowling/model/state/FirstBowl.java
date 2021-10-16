package bowling.model.state;

import bowling.model.Pin;

public class FirstBowl extends Running{
    private final Pin countOfPin;

    public FirstBowl(int countOfPin) {
        this.countOfPin = new Pin(countOfPin);
    }

    @Override
    public State bowl(int countOfPin) {
//        Pin currentPin = this.countOfPin.add(countOfPin);
//        if (currentPin.isStrike()) {
//            return new Spare();
//        }
//
//        return new Miss(this.countOfPin, new Pin(countOfPin));
        return null;
    }
}
