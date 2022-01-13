package bowling.frame;

import bowling.Pins;

public class LastFrame implements Frame {

    @Override
    public Frame bowl(Pins fallenPins) {
        return null;
    }

    @Override
    public String symbol() {
        return null;
    }

    @Override
    public int getFrameNo() {
        return Frame.MAX_FRAME_NO;
    }
}
