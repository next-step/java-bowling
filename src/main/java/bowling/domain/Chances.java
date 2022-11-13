package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Chances {
    private static final int NORMAL_FRAME_MAX_CHANCE_COUNT = 2;
    private static final int FINAL_FRAME_MAX_CHANCE_COUNT = 3;
    private final List<Chance> chances;

    public Chances() {
        this.chances = new ArrayList<>();
    }

    public void add(int knockDownCount) {
        int order = chances.size();
        Pin current = Pin.of(knockDownCount);
        Pin sum = chances.stream()
                .map(Chance::pin)
                .reduce(Pin.of(0), Pin::add)
                .add(current);
        chances.add(new Chance(current, Status.findStatus(order, current, sum)));
    }

    public boolean areAllPinsDown() {
        return chances.stream()
                .map(Chance::pin)
                .reduce(Pin.of(0), Pin::add)
                .areAllPinsDown();
    }

    public boolean noLeftChances(boolean isFinalFrame) {
        if (!isFinalFrame || !areAllPinsDown()) {
            return chances.size() == NORMAL_FRAME_MAX_CHANCE_COUNT;
        }
        return chances.size() == FINAL_FRAME_MAX_CHANCE_COUNT;
    }

    public List<Chance> chances() {
        return Collections.unmodifiableList(chances);
    }

}
