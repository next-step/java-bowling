package domain;

public class Spare extends State {
    private final String STATE_NAME = "Spare";
    private int firstPin;
    private int secondPin;

    Spare(int firstPin, int secondPin) {
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    @Override
    State bowl(int countOfPins) {
        return null;
    }

    @Override
    public int getFellPins() {
        return firstPin + secondPin;
    }

    @Override
    public int getFirstPin() {
        return firstPin;
    }

    @Override
    public int getSecondPin() {
        return secondPin;
    }

    public boolean isNameOfState(String state) {
        return STATE_NAME.equals(state);
    }

    @Override
    public String getNameOfState() {
        return STATE_NAME;
    }

    @Override
    boolean nowPlaying() {
        return Boolean.TRUE;
    }
}
