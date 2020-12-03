package bowling.domain.state.all;

import bowling.domain.pin.Pin;
import bowling.domain.score.Score;
import bowling.domain.score.Scores;
import bowling.domain.state.State;
import bowling.domain.state.rest.Gutter;
import bowling.domain.state.rest.Ordinary;

public class AllPinsFell extends State {
    protected AllPinsFell(int leftTry, Scores scores) {
        super(leftTry, scores);
    }

    @Override
    public State record(Pin pins) {
        if (pins.isAllFell()) {
            return new Strike(leftTry - 1, scores.add(Score.strike()));
        }
        if (pins.isNoneFell()) {
            return new Gutter(leftTry - 1, scores.add(Score.gutter()));
        }
        return new Ordinary(pins, leftTry - 1, scores.add(Score.ordinary(pins.getFellPins())));
    }
}
