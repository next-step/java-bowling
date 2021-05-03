package bowling.domain.state;

import bowling.domain.Pinfall;
import bowling.domain.PointSymbol;
import bowling.domain.PointSymbols;
import bowling.domain.Score;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FrameStateFinalOnce implements FrameState {
    private static final int SPARE_PINFALLS = 10;

    private final Pinfall firstPinfall;

    public FrameStateFinalOnce(Pinfall pinfall) {
        firstPinfall = pinfall;
    }

    @Override
    public FrameState roll(Pinfall secondPinfall) {
        if (isSpare(secondPinfall)) {
            return new FrameStateBonus(Arrays.asList(firstPinfall, secondPinfall));
        }
        return new FrameStateOpen(Arrays.asList(firstPinfall, secondPinfall));
    }

    @Override
    public boolean isRollable() {
        return true;
    }

    @Override
    public PointSymbols pointSymbols() {
        return new PointSymbols(PointSymbol.valueOf(firstPinfall));
    }

    @Override
    public List<Pinfall> pinfalls() {
        return new ArrayList<>(Arrays.asList(firstPinfall));
    }

    @Override
    public Score score() {
        return score(new ArrayList<>());
    }

    @Override
    public Score score(List<Pinfall> bonusPinfalls) {
        return Score.createNotDetermined();
    }

    private boolean isSpare(Pinfall secondPinfall) {
        return firstPinfall.add(secondPinfall).equals(new Pinfall(SPARE_PINFALLS));
    }
}
