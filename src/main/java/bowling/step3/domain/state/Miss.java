package bowling.step3.domain.state;

import bowling.step3.domain.score.Score;

public class Miss extends SecondPitch {
    private static final int ZERO = 0;

    public Miss(Pins firstPins, Pins secondPins) {
        super(firstPins, secondPins);
    }

    @Override
    public String display() {
        return getFirstPins().display() + DELIMITER + getSecondPins().display();
    }

    @Override
    public Score getScore() {
        return new Score(getFirstPins().getCountOfPins() + getSecondPins().getCountOfPins(), ZERO);
    }
}
