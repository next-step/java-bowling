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
    public boolean isRequestingNewBowlingPinsGroup() { //10의 배수이면 계속 새공 요청
        return finalPitches.getPitchesSum() % 10 == 0;
    }

    @Override
    public boolean isMovableToNextFrame() {
        if (finalPitches.getPitchCounts() < 2) {
            return false;
        }
        if (finalPitches.getPitchCounts() == 2 && finalPitches.isAvailableToPitchBonus()) {
            return false;
        }
        return true;
    }
/*
경우의 수
1) X X X
2) X 3 7 (스트라이크 - 논스트라이크)
3) 0 / X (논스트라이크 - 스트라이크)
 */

        /*
        2번 투구 때 결정 >> firstPitch == 10 || firstPitch + secondPitch == 10 이면 보너스 1투구!
            * firstPitch != strike && firstPitch + secondPitch > 10 이면 에러 발생.
        그 외 : 경기 종료
         */

    @Override
    public Frame next() {
        return null;
    }

    @Override
    public void bowl(int hitCounts, BowlingPinsGroup bowlingPinsGroup) {
        finalPitches.throwBall(hitCounts, bowlingPinsGroup);

    }

    @Override
    public int getIndex() {
        return index;
    }
}
