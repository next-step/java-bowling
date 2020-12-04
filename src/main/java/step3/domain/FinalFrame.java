package step3.domain;

import step3.exception.NotExistsNextFrameException;
import step3.type.ResultPitchesType;

import static step3.type.PitchesOrderType.FIRST;
import static step3.type.PitchesOrderType.SECOND;
import static step3.type.ResultPitchesType.STRIKE;

public class FinalFrame implements Frame {
    public static final String ERROR_CURRENT_FRAME_IS_FINAL = "해당 프레임이 마지막 프레임입니다.";
    public static final int MAX_PITCHES = 3;

    private final int frameNo;
    private final BowlingPoints bowlingPoints;
    private final BowlingSymbols bowlingSymbols;
    private boolean completed;

    public FinalFrame(int frameNo) {
        this.frameNo = frameNo;
        this.bowlingPoints = BowlingPoints.of(MAX_PITCHES);
        this.bowlingSymbols =BowlingSymbols.of(MAX_PITCHES);
    }

    @Override
    public int pitches(int pitchesCount) {
        if (!isFinished()) {
            bowlingPoints.push(pitchesCount);
            bowlingSymbols.push(pitchesCount);
            updateComplete();

            return pitchesCount;
        }

        throw new IllegalArgumentException("더 이상 던질 수 없습니다.");
    }

    private void updateComplete() {
        if (bowlingPoints.size() == MAX_PITCHES || isFinalCompletedCondition()) {
            completed = true;
        }
    }

    private boolean isFinalCompletedCondition() {
        return bowlingPoints.size() == 2
                && bowlingPoints.getScore(FIRST, SECOND) < BowlingPoints.STRIKE_VALUE;
    }

    @Override
    public int getFrameNo() {
        return frameNo;
    }

    @Override
    public int getScore() {
        return getCurrentScore();
    }

    @Override
    public int getScore(ResultPitchesType prevType) {
        if (STRIKE.equals(prevType)) {
            return bowlingPoints.getScore(FIRST, SECOND);
        }
        return bowlingPoints.getScore(FIRST);
    }


    @Override
    public int getCurrentScore() {
        return bowlingPoints.getScore();
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
    public String getResultString() {
        return bowlingSymbols.getSymbol();
    }

    @Override
    public boolean isFinished() {
        return completed;
    }

}
