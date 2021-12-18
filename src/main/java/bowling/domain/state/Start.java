package bowling.domain.state;

import bowling.domain.Frame;
import bowling.domain.FrameInfo;
import bowling.domain.Pitch;

public class Start implements State {

    @Override
    public void run(Pitch pitch, Frame frame) {
        pitch.run();
        FrameInfo frameInfo = frame.info();
        frameInfo.addPitch(pitch);
        frame.changeState();
    }

    @Override
    public boolean progressing() {
        return true;
    }

    @Override
    public boolean retryable() {
        return false;
    }

    @Override
    public String toString() {
        return "Start{}";
    }
}
