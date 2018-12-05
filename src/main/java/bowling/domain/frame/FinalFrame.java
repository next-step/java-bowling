package bowling.domain.frame;

import bowling.domain.Pin;
import bowling.domain.record.Record;
import bowling.domain.score.Score;

import static bowling.utils.BowlingConstants.*;

public class FinalFrame implements Frame {

    private int currFrame;
    private Records records;

    FinalFrame(int currFrame) {
        this.currFrame = currFrame;
        this.records = new Records();
    }

    @Override
    public Frame rollBowlingBall(Pin pin) {
        recordFrameResult(pin);

        if(isFinished()) {
            Frames.addToFrames(this);
            return this;
        }
        return this;
    }

    @Override
    public Record recordFrameResult(Pin pin) {
        return records.addToRecords(pin);
    }

    @Override
    public boolean isFinished() {
        if(this.records.isFirstRecordStrike() && !this.records.isLastChance(FINAL_FRAME_CHANCE)) {
            return false;
        }

        if(!this.records.isLastRecordSpare() && this.records.isLastChance(NORMAL_FRAME_CHANCE)) {
            return true;
        }
        return records.isLastChance(FINAL_FRAME_CHANCE);
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

    @Override
    public Score calculateScore() {
        Score score = Score.initialize();
        score = score.calculateScore(this.records.calculateRecordsForFinalFrame());

        return score;
    }

    @Override
    public Score calculateBonus(Score score) {
        if(score.isBonusForSpare()) {
            score = score.calculateScore(this.records.calculateFirstRecord());
        }

        if(score.isBonusForStrike()) {
            score = score.calculateScore(this.records.calculateAfterSecondPitch());
        }

        return score;
    }
}
