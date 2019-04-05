package domain.frame;

import static domain.frame.Frames.LAST_FRAME;

public class LastFrame extends Frame {
    public LastFrame(int number) {
        super(number);

        if(number != LAST_FRAME) {
            throw new IllegalArgumentException("잘못된 프레임 번호입니다.");
        }
    }

    @Override
    public boolean isFinished() {
        if(pins.getSize() > 2) {
            return true;
        }

        return pins.getSize() > 0 && getRecentStatus().isFrameFinished();
    }
}