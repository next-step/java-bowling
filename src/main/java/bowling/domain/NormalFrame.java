package bowling.domain;

import bowling.util.BowlingUtils;
import bowling.view.NormalFrameString;

import java.util.ArrayList;
import java.util.List;

public class NormalFrame implements Frame {
    private static final int MAXIMUM_SHOT_COUNT = 2;
    private static final int MAXIMUM_DOWN_PINS = 10;
    private static final int ZERO = 0;

    private List<Shot> shots = new ArrayList<>();

    private FrameScore frameScore = new FrameScore();

    @Override
    public void playShot(Shot shot) {
        validate(shot);

        shots.add(shot);
    }

    @Override
    public boolean isFinished() {
        if (shots.size() >= MAXIMUM_SHOT_COUNT) {
            return true;
        }

        return remainPins() == ZERO;
    }

    @Override
    public int getPoint() {
        return frameScore.calculateFrameScore();
    }

    @Override
    public int remainPins() {
        int downPins = BowlingUtils.sum(shots);

        return MAXIMUM_DOWN_PINS - downPins;
    }

    @Override
    public String toResultString() {
        if (shots.isEmpty()) {
            return "";
        }

        return NormalFrameString.getString(shots);
    }

    @Override
    public boolean isScoringFinished() {
        return frameScore.isFinished();
    }

    @Override
    public void addPoint(Shot shot) {
        frameScore.addPoint(shot);
    }
}
