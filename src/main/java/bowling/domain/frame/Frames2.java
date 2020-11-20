package bowling.domain.frame;

import bowling.domain.point.Point;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Frames2 {
    private static final int MIN_FRAME_INDEX = 0;
    private static final int MAX_FRAME_INDEX = 10;
    private static final int FRAME_COUNT = 10;
    private static final String FRAME_MIN_ERROR = "프레임은 0보다 커야합니다";
    private static final String FRAME_MAX_ERROR = "프레임은 10보다 클 수 없습니다";
    private static final int ZERO = 0;
    private static final int BASE_INDEX = 0;
    private static final int LAST_NORMAL_FRAME_INDEX = 8;

    private List<Frame3> frames;
    private int currentIndex;

    private Frames2(List<Frame3> frames) {
        validate(frames);
        this.frames = frames;
    }

    public static Frames2 generate() {
        List<Frame3> frames = new ArrayList<>();

        Frame3 normalFrame = Frame3.first();
        frames.add(normalFrame);
        for (int i = 1; i < FRAME_COUNT - 1; i++) {
            normalFrame = normalFrame.next();
            frames.add(normalFrame);
        }
        frames.add(frames.get(frames.size() - 1).last());
        return new Frames2(frames);
    }

    public void roll(int pin) {
        Frame3 frame = this.frames.get(this.currentIndex);
        frame.roll(pin);

        if (!frame.canRoll()) {
            this.currentIndex++;
        }
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    private void validate(List<Frame3> frames) {
        if (frames.size() != FRAME_COUNT) {
            throw new IllegalArgumentException("10개 프레임이 아닙니다.");
        }
    }

    public boolean isDone() {
        return !this.frames.get(this.frames.size() - 1).canRoll();
    }

    public List<FrameResult> getFrameResults() {
        return this.frames.stream()
                .map(Frame3::getFrameResult)
                .collect(Collectors.toList());
    }

}
