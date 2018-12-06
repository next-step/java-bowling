package bowling.domain.frame;

import bowling.domain.Pin;
import bowling.domain.record.Record;
import bowling.domain.record.Spare;
import bowling.domain.record.Strike;
import bowling.domain.score.Score;

import static bowling.utils.BowlingConstants.*;

public class NormalFrame implements Frame {

    private int currFrame;
    private Records records;
    private Frame next;

    public NormalFrame(int currFrame) {
        this.currFrame = currFrame;
        this.records = new Records();
    }

    @Override
    public Frame rollBowlingBall(Pin pin) {
        recordFrameResult(pin);
        if(isFinished()) {
            Frames.addToFrames(this);
            next = Frame.generateNextFrame(this.currFrame + ONE);
            return next;
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

    @Override
    public Score calculateScore() {
        Score score = Score.of(Pin.getInstance(this.records.calculateAfterSecondPitch()), 0);

        if(this.records.isLastRecordSpare())
            score = Score.of(Spare.getInstance().hitPinCount(), SPARE_BONUS_COUNT);

        if(this.records.isLastRecordStrike())
            score = Score.of(Strike.getInstance().hitPinCount(), STRIKE_BONUS_COUNT);

        if(score.hasBonusScore()) {
            try {
                score = next.calculateBonus(score);
            } catch(Exception e) {
                score = Score.initialize();
            }
        }

        return score;
    }

    @Override
    public Score calculateBonus(Score score) {
        if(score.isBonusForSpare()) {
            score = score.calculateScore(this.records.calculateAfterFirstPitch());
        }

        if(score.isBonusForStrike()) {
            score = score.calculateScore(this.records.calculateAfterSecondPitch());
        }

        if(!this.records.isLastRecordStrike()) {
            score = score.calculateScore(ZERO);
        }

        if(score.hasBonusScore()) {
            score = next.calculateBonus(score);
        }

        return score;
    }
}
