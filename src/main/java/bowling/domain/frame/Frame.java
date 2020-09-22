package bowling.domain.frame;

import bowling.domain.Pins;
import bowling.domain.roll.Roll;
import bowling.domain.roll.RollRecord;
import bowling.domain.score.FrameScore;

import static bowling.domain.BowlingErrorMessage.INVALID_FRAME_NUMBER;
import static bowling.domain.BowlingProperty.*;
import static com.google.common.base.Preconditions.checkArgument;

public abstract class Frame {

    private final int number;
    private final RollRecord rollRecord = new RollRecord();
    private boolean complete;

    public Frame(int number) {
        checkArgument(number >= NORMAL_FRAME_START_NUMBER && number <= FINAL_FRAME_NUMBER,
                INVALID_FRAME_NUMBER);

        this.number = number;
    }

    public final void addRoll(Roll roll) {
        rollRecord.add(roll);
    }

    public final int getNumber() {
        return number;
    }

    public final RollRecord getRollRecord() {
        return rollRecord;
    }

    public final Frame nextFrame() {
        return number == NORMAL_FRAME_END_NUMBER
                ? new FinalFrame(number + 1)
                : new NormalFrame(number + 1);
    }

    public final String getMarking() {
        return rollRecord.getMarking();
    }

    public boolean isFinalFrame() {
        return false;
    }

    public final FrameScore getFrameScore() {
        return new FrameScore(FrameState.valueOf(rollRecord), rollRecord.getCountOfPins());
    }

    public final boolean isComplete() {
        return complete;
    }

    public final void setComplete() {
        complete = true;
    }

    public abstract boolean cannotRollMore(Pins pins);
}
