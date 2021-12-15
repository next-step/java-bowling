package bowling.domain.state;

import bowling.domain.FinalFrame;
import bowling.domain.Frame;
import bowling.domain.Pins;

public class Start implements State {

    @Override
    public void pitch(Pins startPins, Pins fallDownPins, Frame frame) {
//        if (startPins.strike(fallDownPins)) {
//            checkFrame(frame);
//            return;
//        }
//        frame.changeState(new Progress());
    }

    private void checkFrame(Frame frame) {
        if (frame instanceof FinalFrame) {
            frame.changeState(new Progress());
        }
        frame.changeState(new End());
    }
}
