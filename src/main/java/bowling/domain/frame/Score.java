package bowling.domain.frame;

import static java.util.stream.Collectors.*;

import java.util.List;

import bowling.domain.frame.state.Pins;

public class Score {
    private final List<Pins> falledPins;

    public Score(List<Pins> falledPins) {
        this.falledPins = falledPins;
    }

    public boolean isStrike() {
        return isFirstBowlFinish() && isStrike(falledPins.get(0));
    }

    public boolean isSpare() {
        return isSecondBowlFinish() && isSpare(falledPins.get(0), falledPins.get(1));
    }

    public List<Integer> getValues() {
        return falledPins.stream()
            .map(Pins::getPins)
            .collect(toList());
    }

    private boolean isFirstBowlFinish() {
        return falledPins.size() == 1;
    }

    private boolean isStrike(Pins pins) {
        return pins.isStrike();
    }

    private boolean isSecondBowlFinish() {
        return falledPins.size() == 2;
    }

    private boolean isSpare(Pins firstPins, Pins secondPins) {
        return firstPins.isSpare(secondPins);
    }
}
