package bowling.domain.state;

public class Miss implements State{

    private final int firstCountOfPins;
    private final int secondCountOfPins;

    public Miss(int firstCountOfPins, int secondCountOfPins) {
        this.firstCountOfPins = firstCountOfPins;
        this.secondCountOfPins = secondCountOfPins;
    }

    @Override
    public State bowl(int countOfPins) {
        return null;
    }

    public int getFirstCountOfPins() {
        return firstCountOfPins;
    }

    public int getSecondCountOfPins() {
        return secondCountOfPins;
    }
}
