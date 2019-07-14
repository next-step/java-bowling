package domain;

abstract class State {
    protected final int STRIKE = 10;
    protected final int FIRST = 0;
    protected final int SECOND = 1;
    protected final int THIRD= 2;

    abstract State bowl(int countOfPins);

    abstract int getPoints();

    abstract Pin getFirstPin();

    abstract Pin getSecondPin();

    abstract boolean isFrameEnd();

    abstract boolean isNameOfState(String state);

    abstract String getNameOfState();

    abstract String getPoint();

    abstract Score getScore();
}
