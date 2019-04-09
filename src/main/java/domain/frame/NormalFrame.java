package domain.frame;

import domain.pin.Pin;

import static domain.frame.Frames.LAST_FRAME;
import static domain.frame.Frames.START_FRAME;

public class NormalFrame extends Frame {

    public NormalFrame(int number, Pin pin) {
        super(number, pin);

        if (number < START_FRAME || number >= LAST_FRAME) {
            throw new IllegalArgumentException("잘못된 프레임 번호입니다.");
        }
    }

    @Override
    public Frame bowl(Pin pin) {
        if (isFinished()) {
            return (next = createNextFrame(pin));
        }

        return super.bowl(pin);
    }

    @Override
    public boolean isFinished() {
        return !statuses.isEmpty() && getLastStatus().isNormalFrameFinished();
    }

    @Override
    public int getScore() {
        if (isFinished() && getLastStatus().isClear()) {
            return pins.getScore() + getBonusScore(getLastStatus().getBonusCount());
        }

        return pins.getScore();
    }

    @Override
    public int getBonusScore(int left) {
        if (!canGetBonusScore(left)) {
            return 0;
        }

        return next.getPinScore(0) + getAdditionalBonusScore(left-1);
    }

    private boolean canGetBonusScore(int left) {
        return left > 0 && next != null && next.getPinsSize() > 0;
    }

    private int getAdditionalBonusScore(int left) {
        if (left <= 0) {
            return 0;
        }

        if (next.getPinsSize() > 1) {
            return next.getPinScore(1);
        }

        return next.getBonusScore(left);
    }
}