package bowling.domain.frame;

import bowling.domain.bowling.BowlingPinsGroup;
import bowling.domain.pitch.FinalPitches;

public class FinalFrame implements Frame {

    private final int index;
    private final FinalPitches finalPitches = new FinalPitches();

    private FinalFrame(int index) {
        this.index = index;
    }

    public static FinalFrame last(int index) {
        return new FinalFrame(index);
    }

    @Override
    public boolean isRequestingNewBowlingPinsGroup() {
        return false;
    }

    @Override
    public boolean isMovableToNextFrame() {
        return false;
    }

    @Override
    public Frame next() {
        /*
        2번 투구 때 결정 >> firstPitch == 10 || firstPitch + secondPitch == 10 이면 보너스 1투구!
            * firstPitch != strike && firstPitch + secondPitch > 10 이면 에러 발생.
        그 외 : 경기 종료
         */
        if (finalPitches.getPitchCounts() < 2) {
            return this;
        }
        if (finalPitches.getPitchCounts() == 2) {
            if (finalPitches.isStrike() || finalPitches.isSpare()) {
                return this;
            }
        }
        return null;
    }

    @Override
    public void bowl(int hitCounts, BowlingPinsGroup bowlingPinsGroup) {

    }

    @Override
    public int getIndex() {
        return index;
    }
}
