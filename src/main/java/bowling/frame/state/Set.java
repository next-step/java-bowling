package bowling.frame.state;

import bowling.global.exception.CannotCalculateException;
import bowling.score.Pin;
import bowling.score.Score;

import java.util.ArrayList;
import java.util.List;

public class Set extends Progress {

    private Set() {
    }

    public static Set init() {
        return new Set();
    }

    @Override
    public State bowl(Pin fellPins) {
        if (fellPins.isStrike()) {
            return Strike.of(fellPins);
        }
        return Next.of(fellPins);
    }

    @Override
    public List<String> getBowlResults() {
        return new ArrayList<>();
    }

    @Override
    public Score calculateScore(Score previousScore) {
        throw new CannotCalculateException();
    }

}
