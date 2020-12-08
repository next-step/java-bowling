package step3.domain;

import step3.exception.NotExistsNextFrameException;
import step3.type.PitchesOrderType;
import step3.type.ResultPitchesType;

import static step3.type.PitchesOrderType.FIRST;
import static step3.type.PitchesOrderType.SECOND;
import static step3.type.ResultPitchesType.STRIKE;

public class FinalFrame extends Frame {
    public static final String ERROR_CURRENT_FRAME_IS_FINAL = "해당 프레임이 마지막 프레임입니다.";
    public static final int MAX_PITCHES = 3;
    public static final int STRIKE_VALUE = 10;

    public FinalFrame(int frameNo) {
        super(frameNo, BowlingSymbols.of(MAX_PITCHES));
    }

    @Override
    public Frame pitches(int pitchesCount) {
        if (!isFinished()) {
            bowlingSymbols.push(pitchesCount);

            return this;
        }

        throw new IllegalArgumentException("더 이상 던질 수 없습니다.");
    }

    private boolean isFinalCompletedCondition() {
        return bowlingSymbols.size() == 2
                && bowlingSymbols.getScore(FIRST, SECOND) < STRIKE_VALUE;
    }


    @Override
    public int getScore() {
        return getCurrentScore();
    }

    @Override
    public int getScore(ResultPitchesType prevType) {
        if (STRIKE.equals(prevType)) {
            return bowlingSymbols.getScore(FIRST, SECOND);
        }
        return bowlingSymbols.getScore(FIRST);
    }


    @Override
    public Frame next() {
        throw new NotExistsNextFrameException(ERROR_CURRENT_FRAME_IS_FINAL);
    }

    @Override
    public boolean hasNext() {
        return false;
    }


    @Override
    public boolean isFinished() {
        return bowlingSymbols.size() == MAX_PITCHES || isFinalCompletedCondition();
    }

    @Override
    public boolean isAllowAggregate() {
        return isFinished();
    }

    @Override
    public boolean isAllowAggregate(ResultPitchesType prevType) {
        if (STRIKE.equals(prevType)) {
            return existsSymbol(FIRST) && existsSymbol(SECOND);
        }
        return existsSymbol(FIRST);
    }

}
