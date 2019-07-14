package domain;

abstract class State {
    private final String STATE_NAME = "State";
    protected final int STRIKE = 10;

    abstract State bowl(int countOfPins);

    abstract int getFellPins();

    abstract int getFirstPin();

    abstract int getSecondPin();

    abstract boolean isNameOfState(String state);

    abstract String getNameOfState();

    abstract boolean nowPlaying();
}
