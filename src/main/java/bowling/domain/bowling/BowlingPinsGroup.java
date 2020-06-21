package bowling.domain.bowling;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class BowlingPinsGroup {

    private final List<BowlingPin> bowlingPins;

    private BowlingPinsGroup(List<BowlingPin> bowlingPins) {
        this.bowlingPins = bowlingPins;
    }

    public static BowlingPinsGroup initiate() {
        List<BowlingPin> bowlingPins = new ArrayList<>();
        IntStream.rangeClosed(BowlingPin.FIRST_INDEX, BowlingPin.LAST_INDEX)
                .forEach(i -> bowlingPins.add(BowlingPin.of(i)));
        bowlingPins.forEach(BowlingPin::initiate);
        return new BowlingPinsGroup(bowlingPins);
    }
}
