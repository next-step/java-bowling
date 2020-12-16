package bowling.domain;

import static bowling.common.SymbolConstants.NOT_THROWN;

public class NullFrame implements Frame{

    private static NullFrame instance = new NullFrame();

    private NullFrame() {}

    public static NullFrame getInstance() {
        return instance;
    }

    @Override
    public boolean isOver() {
        return false;
    }

    @Override
    public void add(int value) {}

    @Override
    public Frame next() {
        return instance;
    }

    @Override
    public String display() {
        return NOT_THROWN.concat(NOT_THROWN);
    }
}
