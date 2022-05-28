package bowling.domain.frame;

import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static bowling.view.OutputView.BLANK;

public class Frames {
    private final List<Frame> frames;

    public Frames(List<Frame> frames) {
        validate(frames);
        this.frames = frames;
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

        if (!lastFrame.isFinal()) {
            return false;
        }

        return lastFrame.isDone();
    }

    @Override
    public String toString() {
        return IntStream.range(FrameNumber.MIN - 1, FrameNumber.MAX)
                .mapToObj(index -> {
                    try {
                        Frame frame = frames.get(index);
                        return frame.toString();
//                        return frame.toString() + COLUMN;

                    } catch (Exception e) {
                        return BLANK;
                    }
                })
                .collect(Collectors.joining());
    }
}
