package bowling.frame.state;

import bowling.score.Pin;

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

}
