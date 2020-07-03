package bowling.step4.domain.frame;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Frames {
    private static final int INIT_NUMBER = 1;
    private static final int READY_POINT = -1;
    private static final String FRAME_FORMAT = "%-4s";
    private static final String FRAME_DELIMITER = "|  ";

    private final LinkedList<Frame> frames = new LinkedList<>();

    public Frames() {
        frames.add(new NormalFrame(INIT_NUMBER));
    }

    public boolean isGameEnd() {
        return isFramesEnd();
    }

    public int getFramesSize() {
        return frames.size();
    }

    public Frame getLastFrame() {
        return frames.getLast();
    }

    public void progress(int pitch) {
        Frame frame = getLastFrame().pitch(pitch);
        if (isOneFrameEnd(frame)){
            nextFrame(frame);
        }
    }

    public boolean isOneFrameEnd(Frame nowFrame) {
        return !nowFrame.equals(getLastFrame());
    }

    private boolean isFramesEnd() {
        return getLastFrame().isGameEnd();
    }

    private void nextFrame(Frame nextFrame) {
        if (!Objects.isNull(nextFrame)){
            frames.add(nextFrame);
        }
    }

    public List<Integer> calculateScores() {
        return frames.stream()
                .filter(frame -> frame.getScore().getScore() != READY_POINT)
                .mapToInt(frame -> frame.getScore().getScore())
                .boxed()
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return frames.stream()
                .map(frame -> String.format(FRAME_FORMAT, frame.toString()))
                .collect(Collectors.joining(FRAME_DELIMITER));
    }
}
