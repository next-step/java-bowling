package bowling.domain.state;

import bowling.domain.Pinfall;
import bowling.domain.PointSymbol;
import bowling.domain.PointSymbols;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FrameStateBonus implements FrameState {
    private final int MAX_PINFALLS = 3;

    private final List<Pinfall> pinfalls;

    public FrameStateBonus(Pinfall pinfall) {
        pinfalls = new ArrayList<>();
        pinfalls.add(pinfall);
    }

    public FrameStateBonus(List<Pinfall> pinfalls) {
        this.pinfalls = pinfalls;
    }

    @Override
    public FrameState roll(Pinfall pinfall) {
        if (!isRollable()) {
            throw new IllegalArgumentException("공을 던질 수 없습니다");
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

    private void replaceSpareSymbol(List<PointSymbol> pointSymbols) {
        if (isSpare()) {
            pointSymbols.set(1, PointSymbol.SPARE);
        }
    }

    private boolean isSpare() {
        if (pinfalls.size() == 1) {
            return false;
        }

        if (isFirstPinfallStrike()) {
            return false;
        }

        return pinfalls.get(0).add(pinfalls.get(1)).equals(new Pinfall(10));
    }

    private boolean isFirstPinfallStrike() {
        return pinfalls.get(0).isStrike();
    }
}
