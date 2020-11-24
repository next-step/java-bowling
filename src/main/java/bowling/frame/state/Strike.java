package bowling.frame.state;

import bowling.score.Pin;
import bowling.score.Score;

import java.util.Arrays;
import java.util.List;

public class Strike extends Done {

    private final Pin currentPins;

    private Strike(Pin currentPins) {
        this.currentPins = currentPins;
    }

    public static Strike of(Pin currentPins) {
        return new Strike(currentPins);
    }

    @Override
    public State bowl(Pin fellPins) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> getBowlResults() {
        return Arrays.asList(currentPins.toString());
    }

    @Override
    public Score getScore() {
        return Score.ofStrike();
    }

}
