package bowling.domain.bowling;

import bowling.domain.exception.BowlingBuildingException;
import bowling.domain.frame.Frame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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

    public BowlingPinsGroup next(Frame frame) {
        return frame.isRequestingNewBowlingPinsGroup() ? initiate() : filterStandingBowlingPins();
    }

    private BowlingPinsGroup filterStandingBowlingPins() {
        List<BowlingPin> nextBowlingPins = bowlingPins.stream()
                .filter(BowlingPin::isStanding)
                .collect(Collectors.toList());
        return new BowlingPinsGroup(nextBowlingPins);
    }

    public void hitByBall(int hitCounts) {
        validateHitCounts(hitCounts);
        IntStream.range(BowlingPin.FIRST_INDEX, hitCounts)
                .forEach(i -> bowlingPins.get(i).hitByBall());
    }

    private void validateHitCounts(int hitCounts) {
        if (hitCounts > bowlingPins.size()) {
            throw new BowlingBuildingException(BowlingBuildingException.INVALID_PITCH);
        }
    }

    public int getBowlingPinCounts() {
        return bowlingPins.size();
    }
}
