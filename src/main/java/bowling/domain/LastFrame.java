package bowling.domain;

import bowling.type.LastFrameState;
import bowling.view.LastFrameString;

import java.util.ArrayList;
import java.util.List;

public class LastFrame implements Frame {
    private static final int MAXIMUM_SHOT_COUNT = 3;
    private List<Shot> shots = new ArrayList<>();

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
    public String toResultString() {
        if (shots.isEmpty()) {
            return "";
        }
        return LastFrameString.getString(shots);
    }
}
