package bowling.domain.frame;

import bowling.domain.Pin;
import bowling.domain.record.Record;
import bowling.domain.record.Spare;
import bowling.domain.record.Strike;

import static bowling.utils.BowlingConstants.*;

public class FinalFrame implements Frame {

    private int currFrame;
    private Records records;

    public FinalFrame(int currFrame) {
        this.currFrame = currFrame;
        records = new Records();
    }

    @Override
    public Frame rollBowlingBall(Pin pin) {
        if(pin.belowMinHit() || pin.exceedMaxHit()) {
            throw new IllegalArgumentException("볼링핀은 0 ~ 10 사이의 값만 가질 수 있음");
        }

        recordFrameResult(pin);

        if(isCompleted()) {
            return this;
        }
        return this;
    }

    @Override
    public Record recordFrameResult(Pin pin) {
        return records.addToRecords(pin);
    }

    @Override
    public boolean isCompleted() {
        if(!isFirstRecordStrike(records) && !isLastRecordSpare(records) && this.records.isLastChance(NORMAL_FRAME_CHANCE)) {
            return true;
        }

        return records.isLastChance(FINAL_FRAME_CHANCE);
    }

    @Override
    public int getCurrFrame() {
        return this.currFrame;
    }

    @Override
    public Records getRecords() {
        return this.records;
    }

    boolean isLastRecordSpare(Records records) {
        return records.findLastRecord().equals(Spare.getInstance());
    }

    boolean isFirstRecordStrike(Records records) {
        return records.findFirstRecord().equals(Strike.getInstance());
    }
}
