package bowling.domain.frame;

import bowling.domain.Pin;
import bowling.domain.record.Record;

import static bowling.utils.BowlingConstants.MAX_FRAME_COUNT;
import static bowling.utils.BowlingConstants.NORMAL_FRAME_CHANCE;
import static bowling.utils.BowlingConstants.ONE;

public class NormalFrame implements Frame {

    private int currFrame;
    private Records records;

    public NormalFrame(int currFrame) {
        this.currFrame = currFrame;
        this.records = new Records();
    }

    @Override
    public Frame rollBowlingBall(Pin pin) {
        if(pin.belowMinHit() || pin.exceedMaxHit()) {
            throw new IllegalArgumentException("볼링핀은 0 ~ 10 사이의 값만 가질 수 있음");
        }

        recordFrameResult(pin);

        if(isFinished()) {
            return Frame.generateNextFrame(this.currFrame + ONE);
        }

        return this;
    }

    @Override
    public Record recordFrameResult(Pin pin) {
        return records.addToRecords(pin);
    }

    @Override
    public boolean isFinished() {
        return this.records.isLastRecordStrike() || this.records.isLastChance(NORMAL_FRAME_CHANCE);
    }

    @Override
    public boolean isLastFrame() {
        return this.currFrame == MAX_FRAME_COUNT;
    }

    @Override
    public int getCurrFrame() {
        return this.currFrame;
    }

    @Override
    public Records getRecords() {
        return this.records;
    }
}
