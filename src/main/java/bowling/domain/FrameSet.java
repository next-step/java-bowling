package bowling.domain;

import java.util.List;
import java.util.function.Function;

public class FrameSet {

    private List<Frame> frames;

    private FrameSet(List<Frame> frames) {
        this.frames = frames;
    }

    public static FrameSet of(List<Frame> frames) {
        return new FrameSet(frames);
    }

    public boolean isEnd() {
        return frames.stream().allMatch(Frame::isEnd);
    }

    public void mark(Function<Frame, Integer> fallDownPinsGetter) {
        frames.stream()
                .forEach(frame -> {
                    while (!frame.isEnd()) {
                        frame.mark(fallDownPinsGetter.apply(frame));
                    }
                });
    }

}

