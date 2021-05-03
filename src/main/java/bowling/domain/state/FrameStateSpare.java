package bowling.domain.state;

import bowling.IllegalRollingSequenceException;
import bowling.domain.Pinfall;
import bowling.domain.PointSymbol;
import bowling.domain.PointSymbols;
import bowling.domain.Score;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FrameStateSpare implements FrameState {
    private final List<Pinfall> pinfalls;

    public FrameStateSpare() {
        pinfalls = new ArrayList<>();
    }

    public FrameStateSpare(List<Pinfall> pinfalls) {
        this.pinfalls = pinfalls;
    }

    @Override
    public FrameState roll(Pinfall pinfall) {
        throw new IllegalRollingSequenceException("공을 굴릴 수 없습니다");
    }

    @Override
    public boolean isRollable() {
        return false;
    }

    @Override
    public PointSymbols pointSymbols() {
        return new PointSymbols(Arrays.asList(PointSymbol.valueOf(firstPinfall(pinfalls)), PointSymbol.SPARE));
    }

    @Override
    public List<Pinfall> pinfalls() {
        return pinfalls;
    }

    @Override
    public Score score() {
        return score(new ArrayList<>());
    }

    @Override
    public Score score(List<Pinfall> bonusPinfalls) {
        if (bonusPinfalls.size() < 1) {
            return Score.createNotDetermined();
        }

        Score bonusScore = Score.create(firstPinfall(bonusPinfalls).number());
        return Score.createSpare().add(bonusScore);
    }

    private Pinfall firstPinfall(List<Pinfall> bonusPinfalls) {
        return bonusPinfalls.get(0);
    }
}
