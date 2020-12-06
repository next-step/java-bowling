package step3.domain;

import step3.exception.InvalidPitchesException;
import step3.type.PitchesOrderType;
import step3.type.ResultPitchesType;

import static java.util.Objects.nonNull;
import static step3.type.PitchesOrderType.FIRST;
import static step3.type.ResultPitchesType.SPARE;
import static step3.type.ResultPitchesType.STRIKE;

public class NormalFrame implements Frame {
    public static final int MAX_PITCHES = 2;
    public static final String ERROR_INVALID_PITCHES = "유효하지 않은 투구수입니다.";
    public static final int STRIKE_VALUE = 10;

    private final int frameNo;
    private final BowlingSymbols bowlingSymbols;
    private final Frame next;

    public NormalFrame(int frameNo) {
        this(frameNo, makeNext(frameNo));
    }

    public NormalFrame(int frameNo, Frame next) {
        this.frameNo = frameNo;
        this.next = next;
        this.bowlingSymbols = BowlingSymbols.of(MAX_PITCHES);
    }

    @Override
    public Frame pitches(int pitchesCount) throws InvalidPitchesException {
        if (!isFinished()) {
            isValidPitchesCount(pitchesCount);
            bowlingSymbols.push(pitchesCount);

            return this;
        }

        return next.pitches(pitchesCount);
    }

    private void isValidPitchesCount(int pitchesCount) {
        if (bowlingSymbols.getScore() + pitchesCount > 10) {
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
        ResultPitchesType type = bowlingSymbols.getType();
        if (STRIKE.equals(type) || SPARE.equals(type)) {
            score += next.getScore(type);
        }
        return score;
    }

    @Override
    public int getScore(ResultPitchesType prevType) {
        ResultPitchesType currentType = bowlingSymbols.getType();

        if (isDouble(prevType, currentType) && !next.hasNext()) {
            return getCurrentScore() + next.getFirstScore();
        }

        if (isDouble(prevType, currentType)) {
            return getCurrentScore() + next.getCurrentScore();
        }

        if (STRIKE.equals(prevType)) {
            return getCurrentScore();
        }

        if (SPARE.equals(prevType)) {
            return getCurrentScore(FIRST);
        }
        return 0;
    }

    private boolean isDouble(ResultPitchesType prevType, ResultPitchesType currentType) {
        return STRIKE.equals(prevType) && STRIKE.equals(currentType);
    }

    @Override
    public ResultPitchesType getType() {
        return bowlingSymbols.getType();
    }

    @Override
    public int getCurrentScore() {
        if (!isFinished()) {
            return 0;
        }
        return bowlingSymbols.getScore();
    }

    private int getCurrentScore(PitchesOrderType type) {
        if (!isFinished()) {
            return 0;
        }
        return bowlingSymbols.getScore(type);
    }

    @Override
    public int getFirstScore() {
        if (!isFinished()) {
            return 0;
        }
        return bowlingSymbols.getScore(FIRST);
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
        return bowlingSymbols.size() == 2 || bowlingSymbols.getType(FIRST).equals(STRIKE);
    }


}
