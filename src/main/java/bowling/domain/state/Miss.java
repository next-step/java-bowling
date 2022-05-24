package bowling.domain.state;

import bowling.domain.Score;

public class Miss extends Finished{
    private final int firstCountOfPins;
    private final int secondCountOfPins;

    public Miss(int firstCountOfPins, int secondCountOfPins) {
        this.firstCountOfPins = firstCountOfPins;
        this.secondCountOfPins = secondCountOfPins;
    }

    @Override
    public Score getScore() {
        return new Score(this.firstCountOfPins+this.secondCountOfPins, 0);
    }

    @Override
    public String expression() {
        if(secondCountOfPins == 0) {
            return firstCountOfPins + "|-";
        }
        return firstCountOfPins + "|" +secondCountOfPins;
    }

    public int getFirstCountOfPins() {
        return firstCountOfPins;
    }

    public int getSecondCountOfPins() {
        return secondCountOfPins;
    }
}
