package bowling.domain.state;

import bowling.domain.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FrameStateStrike implements FrameState {
    @Override
    public FrameState roll(Pinfall pinfall) {
        throw new IllegalArgumentException("공을 굴릴 수 없습니다");
    }

    @Override
    public boolean isRollable() {
        return false;
    }

    @Override
    public PointSymbols pointSymbols() {
        return new PointSymbols(PointSymbol.STRIKE);
    }

    @Override
    public List<Pinfall> pinfalls() {
        return new ArrayList<>(Arrays.asList(new Pinfall(10)));
    }

    @Override
    public Score score() {
        return score(new ArrayList<>());
    }

    @Override
    public Score score(List<Pinfall> bonusPinfalls) {
        if (bonusPinfalls.size() < 2) {
            return Score.createNotDetermined();
        }

        Score bonusScore = Score.create(bonusPinfalls.get(0).number() + bonusPinfalls.get(1).number());

        return Score.create(10).add(bonusScore);
    }
}
