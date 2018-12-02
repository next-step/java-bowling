package bowling.domain.frame;

import bowling.domain.record.Record;
import bowling.domain.record.Strike;

import static bowling.utils.BowlingConstants.*;

public class NormalFrame implements Frame {

    private int currFrame;
    private Records records;

    public NormalFrame(int currFrame) {
        this.currFrame = currFrame;
        this.records = new Records();
    }

    @Override
    public Frame rollBowlingBall(int pinCount) {
        if(pinCount < MIN_HIT || pinCount > MAX_HIT) {
            throw new IllegalArgumentException("볼링핀은 0 ~ 10 사이의 값만 가질 수 있음");
        }

        recordFrameResult(pinCount);

        if(isCompleted()) {
            return Frame.generateNextFrame(this.currFrame + ONE);
        }

        return this;
    }

    @Override
    public Record recordFrameResult(int pinCount) {
        return records.addToRecords(pinCount);
    }

    @Override
    public boolean isCompleted() {
        return this.records.findLastRecord().equals(Strike.getInstance()) || this.records.isLastChance(NORMAL_FRAME_CHANCE);
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
