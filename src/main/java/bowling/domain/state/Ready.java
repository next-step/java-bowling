package bowling.domain.state;

import bowling.domain.frame.Pin;
import bowling.domain.frame.Score;

import java.util.Collections;
import java.util.List;

public class Ready extends Running {

    private Ready() {
    }

    public static Ready getInstance() {
        return InnerInstanceClazz.instance;
    }

    @Override
    public State bowl(Pin pin) {
        if (pin.isMaxCount()) {
            return new Strike();
        }

        return new FirstBowl(pin);
    }

    @Override
    public Score calculateAdditionalScore(Score beforeScore) {
        return beforeScore;
    }

    @Override
    public List<Pin> pins() {
        return Collections.emptyList();
    }

    private static class InnerInstanceClazz {
        private static final Ready instance = new Ready();
    }
}
