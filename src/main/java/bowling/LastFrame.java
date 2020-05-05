package bowling;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class LastFrame {
    private final LinkedList<Pins> fallenPins = new LinkedList<>();

    public LastFrame() {
        fallenPins.add(new Pins());
    }

    public LastFrame bowl(int fallenPinCount) {
        Pins lastPins = fallenPins.getLast();

        if (lastPins.isFinish()) {
            Pins bonusPins = new Pins();
            bonusPins.bowl(fallenPinCount);
            fallenPins.add(bonusPins);

            return this;
        }

        lastPins.bowl(fallenPinCount);

        return this;
    }

    public boolean isGameEnd() {
        return fallenPins.size() == 2 || fallenPins.getLast().isMiss();
    }

    public String getRecord() {
        return fallenPins.stream()
                .map(Pins::getDescription)
                .collect(Collectors.joining("|"));
    }
}
