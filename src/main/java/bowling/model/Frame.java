package bowling.model;

public interface Frame {

    int NUMBER_OF_START = 1;
    int NUMBER_OF_LAST = 10;

    static Frame initialize() {
        return NormalFrame.ofFirst();
    }

    Frame bowling(Pins pin);

    int getIndex();
}
