package bowling.view.dto;

import bowling.model.Pins;
import bowling.model.frame.BallState;
import bowling.model.frame.finalization.BonusHit;
import bowling.model.frame.finalization.BonusThrown;
import bowling.model.frame.state.FirstThrown;
import bowling.model.frame.state.NotThrown;
import bowling.model.frame.state.SecondThrown;
import bowling.model.frame.state.Spare;
import bowling.model.frame.state.Strike;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class StateResponse {

    private final boolean isSpare;
    private final List<Integer> pinsCounts;
    private final List<Integer> bonusCounts;

    private StateResponse(boolean isSpare, List<Integer> pinsCounts, List<Integer> bonusCounts) {
        this.isSpare = isSpare;
        this.pinsCounts = pinsCounts;
        this.bonusCounts = bonusCounts;
    }

    public static StateResponse from(BallState state) {
        if (state instanceof BonusThrown) {
            BonusHit bonusHit = ((BonusThrown) state).bonusHit();
            return from(bonusHit.fromState(), mapCount(bonusHit.counts()));
        }
        return from(state, Collections.emptyList());
    }

    private static StateResponse from(BallState state, List<Integer> bonusCounts) {
        return new StateResponse(state instanceof Spare, mapCount(extractPins(state)), bonusCounts);
    }

    private static List<Pins> extractPins(BallState state) {
        if (state instanceof Strike) {
            return Collections.singletonList(Pins.MAX);
        }
        if (state instanceof Spare) {
            return Collections.singletonList(((Spare) state).firstHit());
        }
        if (state instanceof FirstThrown) {
            return Collections.singletonList(((FirstThrown) state).countOfHit());
        }
        if (state instanceof SecondThrown) {
            SecondThrown secondThrown = (SecondThrown) state;
            return Arrays.asList(secondThrown.firstHit(), secondThrown.secondHit());
        }
        if (state instanceof NotThrown) {
            return Collections.emptyList();
        }
        throw new IllegalArgumentException(String.format("state type(%s) is not supported", state.getClass()));
    }

    private static List<Integer> mapCount(List<Pins> pinsGroup) {
        return pinsGroup.stream()
                .map(Pins::count)
                .collect(Collectors.toList());
    }

    public boolean isSpare() {
        return isSpare;
    }

    public List<Integer> getPinsCounts() {
        return pinsCounts;
    }

    public List<Integer> getBonusCounts() {
        return bonusCounts;
    }
}
