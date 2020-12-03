package bowling.domain.state;

import bowling.domain.pin.Pin;
import bowling.domain.score.Score;
import bowling.domain.score.Scores;
import bowling.domain.state.all.Strike;
import bowling.domain.state.rest.Gutter;
import bowling.domain.state.rest.Ordinary;

import static bowling.domain.frame.Frame.MAX_TRY_COUNT;

public class FrameReady extends State {
    public FrameReady(int leftTry) {
        super(leftTry, Scores.empty());
    }

    @Override
    public State record(Pin pins) {
        if (pins.isAllFell()) {
            return recordStrike();
        }
        if (pins.isNoneFell()) {
            return new Gutter(leftTry - 1, scores.add(Score.gutter()));
        }
        return new Ordinary(pins, leftTry - 1, scores.add(Score.ordinary(pins.getFellPins())));
    }

    private Strike recordStrike() {
        Scores newScores = scores.add(Score.strike());
        if (leftTry == MAX_TRY_COUNT) {
            return new Strike(MIN_LEFT_TRY, newScores);
        }
        return new Strike(leftTry - 1, newScores);
    }

}
