package bowling.frame;

import bowling.Pins;
import bowling.state.Throwing;
import bowling.state.running.Ready;

public class NormalFrame implements Frame {

    private Throwing state;
    private final int frameNo;

    private NormalFrame(int frameNo) {
        this.state = new Ready();
        this.frameNo = frameNo;
    }

    public static NormalFrame first() {
        return new NormalFrame(1);
    }


    @Override
    public Frame bowl(Pins fallenPins) {
        this.state = this.state.bowl(fallenPins);
        if (this.state.isEnd()) {
            return new NormalFrame(this.frameNo + 1);
        }

        return this;
    }

    @Override
    public String symbol() {
        return state.symbol();
    }

    @Override
    public int getFrameNo() {
        return frameNo;
    }
}
