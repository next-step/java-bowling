package bowling.domain.state;

import bowling.IllegalRollingSequenceException;
import bowling.domain.Pinfall;
import bowling.domain.PointSymbol;
import bowling.domain.PointSymbols;
import bowling.domain.Score;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FrameStateOpen implements FrameState {
    private final List<Pinfall> pinfalls;

    public FrameStateOpen() {
        pinfalls = new ArrayList<>();
    }

    public FrameStateOpen(List<Pinfall> pinfalls) {
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
        return new PointSymbols(pinfalls.stream()
                .map(PointSymbol::valueOf)
                .collect(Collectors.toList()));
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
        return pinfalls.stream()
                .map(pinfall -> Score.create(pinfall.number()))
                .reduce(Score::add)
                .orElse(Score.createNotDetermined());
    }
}
