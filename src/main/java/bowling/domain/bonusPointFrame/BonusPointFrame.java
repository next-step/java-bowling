package bowling.domain.bonusPointFrame;

import bowling.domain.frame.Frame;
import bowling.exception.CustomException;
import bowling.exception.ErrorCode;

public class BonusPointFrame {

    private int count;
    private Frame frame;

    public BonusPointFrame(int count, Frame frame) {
        this.count = count;
        this.frame = frame;
    }

    public void addBonusPoint(int point) {
        frame.addPoint(point);
        count--;
    }

    public boolean needMoreBonus() {
        return count > 0;
    }

    public void endScoring() {
        if (count > 0) {
            throw new CustomException(ErrorCode.BONUS_LEFT);
        }
        frame.endScoring();
    }
}
