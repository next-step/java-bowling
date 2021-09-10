package bowling.domain;

import bowling.type.LastFrameState;
import bowling.view.LastFrameString;

import java.util.ArrayList;
import java.util.List;

public class LastFrame implements Frame {
    private List<Shot> shots = new ArrayList<>();
    private FrameScore frameScore = new FrameScore();

    @Override
    public void playShot(Shot shot) {
        validate(shot);

        shots.add(shot);
    }

    @Override
    public boolean isFinished() {
        return !LastFrameState.getState(shots)
                .isPresent();
    }

    @Override
    public int remainPins() {
        return LastFrameState.calculateRemainPins(shots);
    }

    @Override
    public int getPoint() {
        return frameScore.calculateFrameScore();
    }

    @Override
    public String toResultString() {
        if (shots.isEmpty()) {
            return "";
        }
        return LastFrameString.getString(shots);
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
