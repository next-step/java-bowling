package bowling.frame.state;

import bowling.score.Pin;
import bowling.score.Score;

import java.util.Arrays;
import java.util.List;

public class Next extends Progress {

    private final Pin previousPins;

    private Next(Pin previousPins) {
        this.previousPins = previousPins;
    }

    public static Next of(Pin previousPins) {
        return new Next(previousPins);
    }

    @Override
    public State bowl(Pin nextPins) {
        if (previousPins.isSpare(nextPins)) {
            return Spare.of(previousPins, nextPins);
        }
        return Miss.of(previousPins, nextPins);
    }

    @Override
    public List<String> getBowlResults() {
        return Arrays.asList(previousPins.toString());
    }

    @Override
    public Score getScore() {
        throw new UnsupportedOperationException();
    }

}
