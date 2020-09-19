package bowling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

public class Frames {

    private static final int START_FRAME = 0;
    private final Frame startFrame;
    private Frame currentFrame;

    private Frames(Frame startFrame) {
        this.startFrame = startFrame;
        this.currentFrame = startFrame;
    }

    public List<Frame> getFrames() {
        List<Frame> frames = new ArrayList<>(Collections.singletonList(startFrame));
        Frame currentFrame = startFrame;
        while (!currentFrame.isLastFrame()) {
            currentFrame = currentFrame.getNextFrame();
            frames.add(currentFrame);
        }
        return Collections.unmodifiableList(frames);
    }

    public void bowl(Pin pin) {
        if (isFinished()) {
            throw new IllegalStateException("모든 프레임이 완료된 상태 입니다.");
        }
        currentFrame.bowl(pin);
        if (hasNextFrameAndIsCurrentFrameFinished()) {
            currentFrame = currentFrame.getNextFrame();
        }
    }

    private boolean hasNextFrameAndIsCurrentFrameFinished() {
        return !currentFrame.isLastFrame() && currentFrame.isDone();
    }

    public boolean isFinished() {
        return currentFrame.isLastFrame() && currentFrame.isDone();
    }

    public static Frames of(int totalFrames) {
        Frame startFrame = Frame.of();
        AtomicReference<Frame> frame = new AtomicReference<>(startFrame);
        IntStream.range(START_FRAME + 1, totalFrames - 1)
                .forEach(index -> frame.set(frame.get().next()));
        frame.get().last();
        return new Frames(startFrame);
    }
}
