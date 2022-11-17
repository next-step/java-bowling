package bowling.view.dto;

import static java.util.stream.Collectors.*;

import java.util.List;

import bowling.domain.frame.state.Pins;

public class BowlRecord {
    private final List<Pins> falledPins;
    private final boolean isStrike;
    private final boolean isSpare;

    public BowlRecord(List<Pins> falledPins, boolean isStrike, boolean isSpare) {
        this.falledPins = falledPins;
        this.isStrike = isStrike;
        this.isSpare = isSpare;
    }

    public boolean isStrike() {
        return isStrike;
    }

    public boolean isSpare() {
        return isSpare;
    }

    public List<Integer> getValues() {
        return falledPins.stream()
            .map(Pins::getPins)
            .collect(toList());
    }
}
