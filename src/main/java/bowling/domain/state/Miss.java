package bowling.domain.state;

import bowling.domain.Score;

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

    @Override
    public Score getScore() {
        return new Score(this.firstCountOfPins+this.secondCountOfPins, 0);
    }

    public int getFirstCountOfPins() {
        return firstCountOfPins;
    }

    public int getSecondCountOfPins() {
        return secondCountOfPins;
    }
}
