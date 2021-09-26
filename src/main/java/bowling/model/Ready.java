package bowling.model;

public class Ready implements State {
    Point pin;
    public Ready() {
        pin = new Point(0);
    }

    public Ready(Point pin) {
        this.pin = pin;
    }

    @Override
    public State bowl(int countOfPin) {
        Point firstPin = new Point(countOfPin);
        if (firstPin.isStrike()) {
            return new Strike();
        }

        return new FirstBowl(firstPin);
    }
}
