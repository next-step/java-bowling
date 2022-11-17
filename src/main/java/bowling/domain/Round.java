package bowling.domain;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Round {
    private static final int ROUND_COUNT = 10;
    private final List<Frame> frames;

    public Round() {
        frames = IntStream.range(0, ROUND_COUNT - 1)
                .mapToObj(i -> new NormalFrame())
                .collect(Collectors.toList());
        frames.add(new LastFrame());
    }

    public void hit(Hit hit) {
        Frame last = getLastPlayingFrame()
                .orElseThrow(() -> new IllegalStateException("모든 프레임이 종료되었습니다."));
        last.play(hit);
    }

    public boolean isEnd() {
        Optional<Frame> last = getLastPlayingFrame();
        return last.isEmpty();
    }

    public int getCurrentFrameNumber() {
        Optional<Frame> last = getLastPlayingFrame();
        if (last.isEmpty()) {
            return ROUND_COUNT;
        }
        Frame currentFrame = last.get();
        return frames.indexOf(currentFrame) + 1;

    }

    private Optional<Frame> getLastPlayingFrame() {
        return frames.stream()
                .filter(frame -> !frame.isEnd())
                .findFirst();
    }

    public void forEach(Consumer<Frame> consumer) {
        frames.forEach(consumer);
    }
}
