package domain.frame;

import domain.pin.Pin;
import domain.status.Status;

import static domain.frame.Frames.LAST_FRAME;

public class LastFrame extends Frame {

    private static final int LAST_FRAME_FINISH_SIZE = 3;

    public LastFrame(Pin pin) {
        super(LAST_FRAME, pin);
    }

    @Override
    public boolean isFinished() {
        if (statuses.isEmpty()) {
            return false;
        }

        if (pins.size() >= LAST_FRAME_FINISH_SIZE) {
            return true;
        }

        Status lastStatus = getLastStatus();
        return lastStatus.isNormalFrameFinished() && !lastStatus.isClear();
    }

    @Override
    public int getScore() {
        return pins.getScore();
    }

    @Override
    public int getBonusScore(int left) {
        return 0;
    }
}