package bowling.step3.domain.frame;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Frames {
    private static final int INIT_NUMBER = 1;
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int READY_POINT = -1;
    private static final int LAST_FRAME_NO = 10;
    private static final int LAST_FRAME_INDEX = 9;
    private static final String FRAME_FORMAT = "%-4s";
    private static final String FRAME_DELIMITER = "|  ";

    private final LinkedList<Frame> frames = new LinkedList<>();

    public Frames() {}

    public boolean isEnd() {
        return frames.size() == LAST_FRAME_NO && frames.get(LAST_FRAME_INDEX).isFrameEnd();
    }

    public int getFramesSize() {
        return frames.size();
    }

    public Frame getLastFrame() {
        if(isFirst()){
            frames.add(new NormalFrame(INIT_NUMBER));
        }
        return frames.getLast();
    }

    private boolean isFirst() {
        return !Objects.isNull(frames) && frames.size() == ZERO;
    }

    public void progress(int pitch) {
        Frame frame = getLastFrame().pitch(pitch);
        if (isFrameEnd(frame)){
            nextFrame(frame);
        }
    }

    private boolean isFrameEnd(Frame nowFrame) {
        return !nowFrame.equals(getLastFrame());
    }

    private void nextFrame(Frame nextFrame) {
        if (!Objects.isNull(nextFrame)){
            frames.add(nextFrame);
        }
    }

    public List<Integer> calculateScores() {
        List<Integer> scores = frames.stream()
                .filter(frame -> frame.getScore().getScore() != READY_POINT)
                .mapToInt(frame -> frame.getScore().getScore())
                .boxed()
                .collect(Collectors.toList());
        return scores;
    }

    @Override
    public String toString() {
        return frames.stream()
                .map(frame -> String.format(FRAME_FORMAT, frame.toString()))
                .collect(Collectors.joining(FRAME_DELIMITER));
    }
}
