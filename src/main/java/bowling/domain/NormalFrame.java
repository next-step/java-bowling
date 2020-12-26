package bowling.domain;

import static bowling.common.SymbolConstants.*;

public class NormalFrame implements Frame{
    private static final int NORMAL_FRAME_MAX = 9;

    private final int frameNo;
    private final NormalTries normalTries;
    private final Frame next;

    public NormalFrame(int frameNo, NormalTries normalTries) {
        this.frameNo = frameNo;
        this.normalTries = normalTries;
        this.next = next();
    }

    public static Frame first() {
        return new NormalFrame(1, NormalTries.init());
    }

    @Override
    public boolean isOver() {
        return normalTries.isOver();
    }

    @Override
    public void add(int value) {
        normalTries.add(value);
    }

    @Override
    public Frame next() {
        if(frameNo == NORMAL_FRAME_MAX) {
            return FinalFrame.init();
        }

        return new NormalFrame(frameNo + 1, NormalTries.init());
    }

    @Override
    public String display() {
        String first = NOT_THROWN, second = NOT_THROWN;

        if(normalTries.first().isPresent()) {
            first = normalTries.first().get().display();
        }

        if(normalTries.second().isPresent()) {
            second = normalTries.second().get().display();
        }

        if(normalTries.isFirstNotThrown()) {
            return NOT_THROWN.concat(NOT_THROWN);
        }

        if(normalTries.isStrike()) {
            return first.concat(NOT_THROWN);
        }

        if(normalTries.isSecondNotThrown()) {
            return first.concat(SYMBOL_DELIMITER).concat(NOT_THROWN);
        }

        if(normalTries.isSpare()) {
            return first.concat(SYMBOL_DELIMITER).concat(SPARE);
        }

        return first.concat(SYMBOL_DELIMITER).concat(second);
    }

    @Override
    public int getScore() {
        Score score = normalTries.fetchScore();

        if( isOver() && !score.canBeCalculated() ) {
            return next.addPreviousScore(score).value();
        }

        if(!score.canBeCalculated()) {
            return Score.NOT_CALCULATED;
        }

        return score.value();
    }

    @Override
    public Score addPreviousScore(Score prevScore) {
        Score score = normalTries.calculatePreviousScore(prevScore);
        if(score.canBeCalculated()) {
            return score;
        }
        return next.addPreviousScore(score);
    }

    @Override
    public Frame getNext() {
        return next;
    }

}
