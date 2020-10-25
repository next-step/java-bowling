package bowling.frame.state;

import bowling.score.Pin;

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
    public String toString() {
        return null;
    }
}
