package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Frames {
    private static final int MIN_FRAME_INDEX = 0;
    private static final int MAX_FRAME_INDEX = 10;
    private static final int FRAME_COUNT = 10;
    private static final String FRAME_MIN_ERROR = "프레임은 0보다 커야합니다";
    private static final String FRAME_MAX_ERROR = "프레임은 10보다 클 수 없습니다";
    private static final int ZERO = 0;
    private static final int BASE_INDEX = 0;
    private static final int LAST_NORMAL_FRAME_INDEX = 8;
    public static final String FRAME_VALID_ERROR = "10개 프레임이 아닙니다.";

    private List<Frame> frames;
    private int currentIndex;

    private Frames(List<Frame> frames) {
        validate(frames);
        this.frames = frames;
    }

    public static Frames generate() {
        List<Frame> frames = new ArrayList<>();

        Frame normalFrame = Frame.first();
        frames.add(normalFrame);
        for (int i = 1; i < FRAME_COUNT - 1; i++) {
            normalFrame = normalFrame.next();
            frames.add(normalFrame);
        }
        frames.add(frames.get(frames.size() - 1).last());
        return new Frames(frames);
    }

    public void roll(int pin) {
        Frame frame = this.frames.get(this.currentIndex);
        frame.roll(pin);

        if (!frame.canRoll()) {
            this.currentIndex++;
        }
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    private void validate(List<Frame> frames) {
        if (frames.size() != FRAME_COUNT) {
            throw new IllegalArgumentException(FRAME_VALID_ERROR);
        }
    }

    public boolean isDone() {
        return !this.frames.get(this.frames.size() - 1).canRoll();
    }

    public List<FrameResult> getFrameResults() {
        return this.frames.stream()
                .map(Frame::getFrameResult)
                .collect(Collectors.toList());
    }

}
