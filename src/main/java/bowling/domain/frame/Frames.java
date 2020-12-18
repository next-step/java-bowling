package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Frames {

    private static final int FRAME_COUNT = 10;

    private final List<Frame> frames;
    private int currentFrameNumber;

    private Frames(List<Frame> frames) {
        validate(frames);

        this.frames = frames;
    }

    public static Frames create() {
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

    private void validate(List<Frame> frames) {
        if (frames.size() != FRAME_COUNT) {
            throw new IllegalArgumentException("10개 프레임이 아닙니다.");
        }
    }

    public void roll(int downPin) {
        Frame frame = this.frames.get(this.currentFrameNumber);
        frame.roll(downPin);

        if (!frame.hasTurn()) {
            this.currentFrameNumber++;
        }
    }

    public List<FrameResult> getFrameResults() {
        return this.frames.stream()
            .map(Frame::getFrameResult)
            .collect(Collectors.toList());
    }


    public int getCurrentFrameNumber() {
        return this.currentFrameNumber;
    }

    public boolean isFinished() {
        return !this.frames.get(this.frames.size() - 1).hasTurn();
    }
}
