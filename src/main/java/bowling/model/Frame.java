package bowling.model;

public interface Frame {

    int NUMBER_OF_START = 1;
    int NUMBER_OF_LAST = 10;

    static Frame generate(int index) {
        if (NUMBER_OF_LAST == index) {
//            return new FinalFrame();
        }
        return NormalFrame.of(index);
    }

    static Frame initialize() {
        return NormalFrame.ofFirst();
    }

    Frame bowling(Pins pin);

    int getIndex();
}
