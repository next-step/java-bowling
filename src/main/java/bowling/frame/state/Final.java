package bowling.frame.state;

import bowling.score.Pin;
import bowling.score.Score;

import java.util.Arrays;
import java.util.List;

public class Final extends Done {

    private final Pin currentPins;

    private Final(Pin currentPins) {
        this.currentPins = currentPins;
    }

    public static State from(Pin currentPins) {
        if (currentPins.isStrike()) {
            return Strike.of(currentPins);
        }
        return new Final(currentPins);
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
        return Score.of(currentPins.getFellPins());
    }

}
