package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;

public class LastFrame {

    private final List<Integer> downedPins;

    public LastFrame() {
        this.downedPins = new ArrayList<>();
    }

    public void record(int downedPin) {
        downedPins.add(downedPin);
    }

    public boolean isEnd() {
        return (downedPins.size() == 3 && downedPins.get(0) == 10) || (downedPins.size() == 2 && downedPins.get(0) != 10);
    }
}
