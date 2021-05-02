package bowling.domain.state;

import bowling.IllegalRollingSequenceException;
import bowling.domain.Pinfall;
import bowling.domain.PointSymbol;
import bowling.domain.PointSymbols;
import bowling.domain.Score;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FrameStateBonus implements FrameState {
    private static final int MAX_PINFALLS = 3;

    private final List<Pinfall> pinfalls;

    public FrameStateBonus(Pinfall pinfall) {
        pinfalls = new ArrayList<>();
        pinfalls.add(pinfall);
    }

    public FrameStateBonus(List<Pinfall> pinfalls) {
        this.pinfalls = new ArrayList<>();
        this.pinfalls.addAll(pinfalls);
    }

    @Override
    public FrameState roll(Pinfall pinfall) {
        if (!isRollable()) {
            throw new IllegalRollingSequenceException("공을 던질 수 없습니다");
        }

        pinfalls.add(pinfall);
        return this;
    }

    @Override
    public boolean isRollable() {
        return pinfalls.size() < MAX_PINFALLS;
    }

    @Override
    public PointSymbols pointSymbols() {
        List<PointSymbol> pointSymbols = pinfalls.stream()
                .map(PointSymbol::valueOf)
                .collect(Collectors.toList());
        replaceSpareSymbol(pointSymbols);
        return new PointSymbols(pointSymbols);
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
        if (isRollable()) {
            return Score.createNotDetermined();
        }

        Score score = pinfalls.stream().reduce(Score.create(0),
                (subTotalScore, pinfall) -> {
                    subTotalScore = subTotalScore.add(Score.create(pinfall.number()));
                    return subTotalScore;
                }, (a, b) -> a);

        return score;
    }

    private void replaceSpareSymbol(List<PointSymbol> pointSymbols) {
        if (isSecondPinfallSpare()) {
            pointSymbols.set(1, PointSymbol.SPARE);
            return;
        }

        if (isThirdPinfallSpare()) {
            pointSymbols.set(2, PointSymbol.SPARE);
        }
    }

    private boolean isSecondPinfallSpare() {
        if (pinfalls.size() == 1) {
            return false;
        }

        return isSpare(pinfalls.get(0), pinfalls.get(1));
    }

    private boolean isThirdPinfallSpare() {
        if (pinfalls.size() != 3) {
            return false;
        }
        return isSpare(pinfalls.get(1), pinfalls.get(2));
    }

    private boolean isSpare(Pinfall first, Pinfall second) {
        if (first.isStrike() || second.isStrike()) {
            return false;
        }
        return first.add(second).equals(new Pinfall(10));
    }
}
