package bowling.domain.frame;

import bowling.domain.State.Pin;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static bowling.view.OutputView.BLANK;

public class Frames {
    private final List<Frame> frames;

    public Frames(List<Frame> frames) {
        validate(frames);
        this.frames = new ArrayList<>(frames);
    }

    private void validate(List<Frame> frames) {
        if (CollectionUtils.isEmpty(frames)) {
            throw new IllegalArgumentException("frames는 빈 값 일 수 없습니다.");
        }
    }

    public static Frames initialize() {
        return new Frames(List.of(Frame.initialize()));
    }

    public FrameNumber currentNumber() {
        return lastFrame().number();
    }

    private Frame lastFrame() {
        return frames.get(frames.size() - 1);
    }

    public boolean isDone() {
        Frame lastFrame = lastFrame();

        if (lastFrame.isNormal()) {
            return false;
        }

        return lastFrame.isDone();
    }

    public void bowl(Pin pin) {
        Frame lastFrame = lastFrame();
        Frame nextFrame = lastFrame().bowl(pin);

        if (lastFrame.isDone() && lastFrame.isNormal()) {
            frames.add(nextFrame);
        }
    }

    private Frame getFrameOrNull(int index) {
        if (frames.size() - 1 >= index) {
            return frames.get(index);
        }
        return null;
    }

    private String toString(Frame frame) {
        if (frame == null) {
            return BLANK;
        }
        return frame.toString();
    }

    @Override
    public String toString() {
        return IntStream.range(FrameNumber.MIN - 1, FrameNumber.MAX)
                .mapToObj(index -> toString(getFrameOrNull(index)))
                .collect(Collectors.joining());
    }
}
