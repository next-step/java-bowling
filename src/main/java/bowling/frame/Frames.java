package bowling.frame;

import bowling.pin.Pin;
import bowling.state.Ready;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    public static final int FIRST_FRAME_OF_BOWLING_GAME = 1;
    public static final int LIMIT_FRAME_OF_BOWLING_GAME = 10;

    private final List<Frame> frames;

    private Frames() {
        this.frames = new ArrayList<>(LIMIT_FRAME_OF_BOWLING_GAME);
        frames.add(NormalFrame.init(Ready.init()));
    }

    public static Frames init() {
        return new Frames();
    }

    public void pitch(final Pin pin) {
        Frame frame = getLastFrame();
        frame.play(pin);
        ifItNormalFrameProceedNextFrame(frame);
    }

    private void ifItNormalFrameProceedNextFrame(final Frame frame) {
        if (frame instanceof NormalFrame) {
            ((NormalFrame) frame).proceed(frames);
        }
    }

    private Frame getLastFrame() {
        return frames.get(frames.size() - 1);
    }

    public boolean isEnd() {
        return getLastFrame() instanceof LastFrame && !getLastFrame().hasTurn();
    }
}
