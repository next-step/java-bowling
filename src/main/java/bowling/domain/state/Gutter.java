package bowling.domain.state;

import bowling.domain.pin.Pin;
import bowling.domain.score.Score;
import bowling.domain.score.Scores;

public class Gutter extends State {
    public Gutter(int leftTry, Scores scores) {
        super(leftTry, scores);
    }

    @Override
    public State record(Pin pins) {
        if (pins.isAllFell()) {
            return new Spare(leftTry - 1, scores.add(Score.spare(pins.getFellPins())));
        }
        if (pins.isNoneFell()) {
            return new Gutter(MIN_LEFT_TRY, scores.add(Score.gutter()));
        }
        return new Ordinary(pins, MIN_LEFT_TRY, scores.add(Score.ordinary(pins.getFellPins())));
    }

}
