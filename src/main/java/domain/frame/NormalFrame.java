package domain.frame;

import static domain.frame.Frames.LAST_FRAME;
import static domain.frame.Frames.START_FRAME;

public class NormalFrame extends Frame {

    public NormalFrame(int number) {
        super(number);

        if(number < START_FRAME || number >= LAST_FRAME) {
            throw new IllegalArgumentException("잘못된 프레임 번호입니다.");
        }
    }

    @Override
    public boolean isFinished() {
        return pins.getSize() > 0 && getRecentStatus().isFrameFinished();
    }
}