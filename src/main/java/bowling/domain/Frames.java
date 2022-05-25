package bowling.domain;

import bowling.exception.IllegalFramesException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {
    private static final String FRAMES_NULL_OR_EMPTY_ERROR_MESSAGE = "프레임들이 비어있습니다.";
    private static final String FRAMES_OVER_STANDARD_ERROR_MESSAGE = "한 게임의 프레임을 10프레임까지 입니다. (입력된 프레임: %s개)";
    private static final int FRAMES_BOUNDARY = 10;

    private final List<Frame> values;

    private Frames(List<Frame> values) {
        validate(values);
        this.values = values;
    }

    public static Frames create() {
        return new Frames(new ArrayList<>(Collections.singletonList(NormalFrame.initialize())));
    }

    public static Frames create(List<Frame> values) {
        return new Frames(values);
    }

    private void validate(List<Frame> values) {
        validateNullAndEmpty(values);
        validateFramesBoundary(values);
    }

    private void validateFramesBoundary(List<Frame> values) {
        if (values.size() > FRAMES_BOUNDARY) {
            throw new IllegalFramesException(FRAMES_OVER_STANDARD_ERROR_MESSAGE, String.valueOf(values.size()));
        }
    }

    private void validateNullAndEmpty(List<Frame> values) {
        if (values == null || values.isEmpty()) {
            throw new IllegalFramesException(FRAMES_NULL_OR_EMPTY_ERROR_MESSAGE);
        }
    }

    public void bowl(Pitching pitching) {
        Frame currentFrame = currentFrame();
        Frame resultFrame = currentFrame.bowl(pitching);
        if (isPossibleToCreate(currentFrame, resultFrame)) {
            values.add(resultFrame);
        }
    }

    private Frame currentFrame() {
        return values.get(values.size() - 1);
    }

    public int getCurrentFrameNumber() {
        return currentFrame().getFrameNumber();
    }

    public boolean isNextPitching() {
        return !(currentFrame().isEnd() && isLastFrame());
    }

    private boolean isLastFrame() {
        return values.size() == FrameNumber.MAX_FRAME_NUMBER;
    }

    public List<Frame> getValues() {
        return values;
    }

    private boolean isPossibleToCreate(Frame currentFrame, Frame resultFrame) {
        return currentFrame.isEnd() && !resultFrame.isEnd();
    }
}
