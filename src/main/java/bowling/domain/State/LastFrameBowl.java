package bowling.domain.State;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LastFrameBowl extends Running {
    private List<Pins> rolls = new ArrayList<>();
    private int count = 2;

    public LastFrameBowl(boolean bonusFlag) {
        if (bonusFlag) {
            count += 1;
        }
    }

    @Override
    public State bowl(int felledPins) {
        if (rolls.size() == count) {
            throw new UnsupportedOperationException("공을 투구할 횟수를 초과하였습니다.");
        }
        Pins pins = Pins.bowl(felledPins);
        rolls.add(pins);
        return this;
    }

    @Override
    public String getDesc() {
        return rolls.stream()
                    .map(pins -> displayText(pins))
                    .collect(Collectors.joining(DELIMITER));
    }

    private String displayText(Pins pins) {
        if (pins.isStrike()) {
            return STRIKE;
        }
        return pins.getDisplayPins(pins.getFelledPins());
    }
}
