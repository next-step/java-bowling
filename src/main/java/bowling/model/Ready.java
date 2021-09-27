package bowling.model;

public class Ready implements State {
    Point countOfPin;
    
    public Ready() {
        this.countOfPin = new Point(0);
    }

    public Ready(Point countOfPin) {
        this.countOfPin = countOfPin;
    }

    @Override
    public State bowl(int countOfPin) {
        Point currentPin = new Point(countOfPin);

        if (currentPin.isStrike()) {
            return new Strike();
        }

        return new FirstBowl(countOfPin);
    }
}
