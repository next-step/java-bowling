package bowling.domain.state;

import bowling.domain.Pinfall;
import bowling.domain.PointSymbols;
import bowling.domain.Score;

import java.util.ArrayList;
import java.util.List;

public class FrameStateFinalReady implements FrameState {
    @Override
    public FrameState roll(Pinfall pinfall) {
        if (pinfall.isStrike()){
            return new FrameStateBonus(pinfall);
        }
        return new FrameStateFinalOnce(pinfall);
    }

    @Override
    public boolean isRollable() {
        return true;
    }

    @Override
    public PointSymbols pointSymbols() {
        return new PointSymbols();
    }

    @Override
    public List<Pinfall> pinfalls() {
        return new ArrayList<>();
    }

    @Override
    public Score score() {
        return score(new ArrayList<>());
    }

    @Override
    public Score score(List<Pinfall> bonusPinfalls) {
        return Score.createNotDetermined();
    }


}
