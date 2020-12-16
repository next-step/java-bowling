package bowling.domain;

import static bowling.common.SymbolConstants.NOT_THROWN;
import static bowling.common.SymbolConstants.SPARE;

public class NormalFrame implements Frame{
    private static final int NORMAL_FRAME_MAX = 9;

    private final int frameNo;
    private final NormalTries normalTries;

    public NormalFrame(int frameNo, NormalTries normalTries) {
        this.frameNo = frameNo;
        this.normalTries = normalTries;
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
        String first = normalTries.isFirstNotThrown() ? NOT_THROWN : normalTries.first().display();
        String second = normalTries.isSecondNotThrown() ? NOT_THROWN : normalTries.second().display();

        if(normalTries.isStrike()) {
            return first;
        }

        if(normalTries.isSpare()) {
            return first.concat(SPARE);
        }

        return first.concat(second);
    }
}
