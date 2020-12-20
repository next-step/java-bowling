package bowling.domain;

public class Miss implements State {
    private Pins firstPins;
    private Pins secondPins;

    public Miss(Pins firstPins, Pins secondPins) {
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    @Override
    public Score getScore() {
        Score score = new Score(firstPins);
        score.setSecond(secondPins);
        return score;
    }

    @Override
    public String toString() {
        return "1";
    }
}
