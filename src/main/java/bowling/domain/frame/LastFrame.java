package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;

public class LastFrame extends Frame {

    private final List<Integer> downedPins;

    public LastFrame() {
        this.downedPins = new ArrayList<>();
    }

    @Override
    public void record(int downedPin) {
        downedPins.add(downedPin);
    }

    @Override
    public boolean isEnd() {
        return (downedPins.size() == 3 && downedPins.get(0) == 10)
                || (downedPins.size() == 2 && downedPins.get(0) != 10 && downedPins.get(0) + downedPins.get(1) != 10)
                || (downedPins.size() == 3 && (downedPins.get(0) + downedPins.get(1)) == 10);
    }
}
