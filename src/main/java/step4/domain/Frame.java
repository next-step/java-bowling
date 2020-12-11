package step4.domain;

import step4.type.PitchesOrderType;
import step4.type.ResultPitchesType;

public abstract class Frame {

    protected final int frameNo;
    protected final BowlingSymbols bowlingSymbols;

    public Frame(int frameNo, BowlingSymbols bowlingSymbols) {
        this.frameNo = frameNo;
        this.bowlingSymbols = bowlingSymbols;
    }

    public int getFrameNo() {
        return frameNo;
    }

    public ResultPitchesType getType() {
        return bowlingSymbols.getType();
    }

    public boolean existsSymbol(PitchesOrderType first) {
        return bowlingSymbols.existsSymbol(first);
    }

    public int getCurrentScore() {
        return bowlingSymbols.getScore();
    }

    public String getResultString() {
        return bowlingSymbols.getSymbol();
    }

    public int getScoreByOrderType(PitchesOrderType type) {
        return bowlingSymbols.getScore(type);
    }

    public abstract Frame pitches(int pitchesCount);

    public abstract int getScore();

    public abstract int getScore(ResultPitchesType prevType);

    public abstract Frame next();

    public abstract boolean hasNext();

    public abstract boolean isFinished();

    public abstract boolean isAllowAggregate();

    public abstract boolean isAllowAggregate(ResultPitchesType type);
}
