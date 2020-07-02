package bowling.step3.domain.state;

import bowling.step3.domain.score.Score;

public class Spare extends SecondPitch {
    private static final String SPARE = "/";

    public Spare(Pins firstPins, Pins pins) {
        super(firstPins, pins);
    }

    @Override
    public String display() {
        return getFirstPins().display() + DELIMITER +SPARE;
    }

    @Override
    public Score getScore() {
        return Score.spare();
    }
}
