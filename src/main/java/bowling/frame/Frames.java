package bowling.frame;

import bowling.dto.StateDtos;
import bowling.pin.Pin;
import bowling.state.Ready;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

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
        final Frame frame = getLastFrame();
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
        return isEqualsLastFrame() && isHasNotTurn();
    }

    private boolean isEqualsLastFrame() {
        return getLastFrame() instanceof LastFrame;
    }

    private boolean isHasNotTurn() {
        return !(getLastFrame().hasTurn());
    }

    public List<StateDtos> convertToStateDtosList() {
        return frames.stream()
                .map(Frame::convertToStateDtos)
                .collect(collectingAndThen(toList(), Collections::unmodifiableList));
    }
}
