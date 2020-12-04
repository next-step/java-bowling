package step3.domain;

import step3.exception.InvalidPitchesException;
import step3.type.ResultPitchesType;

import static java.util.Objects.nonNull;
import static step3.type.PitchesOrderType.FIRST;
import static step3.type.ResultPitchesType.SPARE;
import static step3.type.ResultPitchesType.STRIKE;

public class NormalFrame implements Frame {
    public static final int MAX_PITCHES = 2;
    public static final String ERROR_INVALID_PITCHES = "유효하지 않은 투구수입니다.";

    private final int frameNo;
    private final BowlingPoints bowlingPoints;
    private final BowlingSymbols bowlingSymbols;
    private final Frame next;
    private boolean completed;

    public NormalFrame(int frameNo) {
        this(frameNo, makeNext(frameNo));
    }

    public NormalFrame(int frameNo, Frame next) {
        this.frameNo = frameNo;
        this.next = next;
        this.bowlingPoints = BowlingPoints.of(MAX_PITCHES);
        this.bowlingSymbols = BowlingSymbols.of(MAX_PITCHES);
        this.completed = false;
    }

    @Override
    public int pitches(int pitchesCount) throws InvalidPitchesException {
        if (!isFinished()) {
            isValidPitchesCount(pitchesCount);
            bowlingPoints.push(pitchesCount);
            bowlingSymbols.push(pitchesCount);
            updateCompleted();

            return pitchesCount;
        }

        return next.pitches(pitchesCount);
    }

    private void updateCompleted() {
        if (bowlingPoints.size() == 2 || bowlingSymbols.getType(FIRST).equals(STRIKE)) {
            completed = true;
        }
    }

    private void isValidPitchesCount(int pitchesCount) {
        if (getScore() + pitchesCount > 10) {
            throw new IllegalArgumentException(ERROR_INVALID_PITCHES);
        }
    }

    @Override
    public int getFrameNo() {
        return frameNo;
    }

    @Override
    public int getScore() {
        int score = getCurrentScore();
        ResultPitchesType type = bowlingPoints.getType();
        if (STRIKE.equals(type) || SPARE.equals(type)) {
            score += next.getScore(type);
        }
        return score;
    }

    @Override
    public int getScore(ResultPitchesType prevType) {
        ResultPitchesType currentType = bowlingPoints.getType();
        if (STRIKE.equals(prevType) && STRIKE.equals(currentType)) {
            return getCurrentScore() + next.getCurrentScore();
        }
        if (STRIKE.equals(prevType)) {
            return bowlingPoints.getScore();
        }
        if (SPARE.equals(prevType)) {
            return bowlingPoints.getScore(FIRST);
        }
        return 0;
    }

    @Override
    public int getCurrentScore() {
        return bowlingPoints.getScore();
    }

    @Override
    public Frame next() {
        return next;
    }

    @Override
    public boolean hasNext() {
        return nonNull(next);
    }

    public static Frame makeNext(int frameNo) {
        if (frameNo == BowlingGame.FRAME_LAST_NO) {
            return new FinalFrame(frameNo + 1);
        }
        return new NormalFrame(frameNo + 1);
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
