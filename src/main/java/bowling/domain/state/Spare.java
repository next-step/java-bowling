package bowling.domain.state;

import bowling.domain.pin.Pin;
import bowling.domain.score.Score;
import bowling.domain.score.Scores;

public class Spare extends State {

    public Spare(int leftTry, Scores scores) {
        super(leftTry, scores);
    }

    @Override
    public State record(Pin pins) {
        if (pins.isStrike()) {
            return new Strike(leftTry - 1, scores.add(Score.strike()));
        }
        if (pins.isGutter()) {
            return new Gutter(leftTry - 1, scores.add(Score.gutter()));
        }
        return new Ordinary(pins, leftTry - 1, scores.add(Score.ordinary(pins.getPins())));
    }

}
