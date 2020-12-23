package bowling.domain;

import static bowling.common.SymbolConstants.NOT_THROWN;
import static bowling.common.SymbolConstants.SPARE;

public class FinalFrame implements Frame{
    private final int frameNo;
    private final FinalTries finalTries;

    private FinalFrame(FinalTries finalTries) {
        this.frameNo = 10;
        this.finalTries = finalTries;
    }

    public static FinalFrame init() {
        return new FinalFrame(FinalTries.init());
    }

    @Override
    public boolean isOver() {
        return finalTries.isOver();
    }

    @Override
    public void add(int value) {
        finalTries.add(value);
    }

    @Override
    public Frame next() {
        return NullFrame.getInstance();
    }

    @Override
    public String display() {
        String first = finalTries.first().isPresent() ? finalTries.first().get().display() : NOT_THROWN;
        String second = finalTries.second().isPresent() ? finalTries.second().get().display() : NOT_THROWN;
        String third = finalTries.third().isPresent() ? finalTries.third().get().display() : NOT_THROWN;

        if(!finalTries.isStrike() && finalTries.isSpare()) {
            return first.concat(SPARE).concat(third);
        }

        return first.concat(second).concat(third);
    }
}
