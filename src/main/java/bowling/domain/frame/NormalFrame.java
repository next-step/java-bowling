package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;

public class NormalFrame {

    private final List<Integer> downedPins;

    public NormalFrame() {
        downedPins = new ArrayList<>();
    }

    public void record(int downedPin) {
        downedPins.add(downedPin);
    }

    public boolean isEnd() {
        return downedPins.size() == 2;
    }
}
