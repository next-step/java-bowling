package domain.frame;

import domain.pin.Pin;
import domain.score.Score;
import domain.status.Status;

import static domain.frame.Frames.LAST_FRAME;
import static domain.frame.Frames.START_FRAME;

public class NormalFrame extends Frame {

    public NormalFrame(int number, Pin pin) {
        this(number, pin, null);
    }

    public NormalFrame(int number, Pin pin, Frame previous) {
        super(number, pin, previous);

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
            return getScoreWithBonus(getLastStatus());
        }

        return pins.getScore();
    }

    private int getScoreWithBonus(Status status) {
        Score score = status.getScore();
        while (canMoveToNextStatus(score, status)) {
            status = status.getNext();
            score = score.bowl(status.getCurrentPin());
        }

        return score.getCalculatedScore();
    }

    private boolean canMoveToNextStatus(Score score, Status status) {
        return !score.isScoreCalculationFinished() && status.hasNext();
    }

    @Override
    public boolean isScoreCalculationFinished() {
        if (!isFinished()) {
            return false;
        }

        return isScoreCalculationFinished(getLastStatus());
    }

    private boolean isScoreCalculationFinished(Status status) {
        int left = status.getScore().getLeft();

        if (left <= 0) {
            return true;
        }

        while (status.hasNext()) {
            if (--left <= 0) {
                return true;
            }

            status = status.getNext();
        }

        return false;
    }
}