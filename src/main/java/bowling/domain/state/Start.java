package bowling.domain.state;

import bowling.domain.FinalFrame;
import bowling.domain.Frame;
import bowling.domain.Pins;

public class Start implements State {

    @Override
    public void pitch(Pins existPins, Pins fallDownPins, Frame frame) {
        if (existPins.isStrike(fallDownPins)) {
            checkFrame(frame);
            return;
        }
        frame.changeState(new Progress());
    }

    @Override
    public boolean progressing() {
        return true;
    }

    private void checkFrame(Frame frame) {
        if (frame instanceof FinalFrame) {
            frame.changeState(new Progress(true));
            return;
        }
        frame.changeState(new End());
    }

    @Override
    public String toString() {
        return "Start{}";
    }
}
