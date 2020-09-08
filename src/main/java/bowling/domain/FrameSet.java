package bowling.domain;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FrameSet {
    public static final int FRAME_COUNT = 10;

    private Bowler bowler;
    private List<Frame> frames;

    private FrameSet(String name, int frameCount) {
        if (frameCount != FRAME_COUNT) {
            throw new IllegalArgumentException(String.format("프레임 수는 %s보다 많거나 작을 수 없습니다. [%s]", FRAME_COUNT, frameCount));
        }

        bowler = Bowler.from(name);
        frames = IntStream.rangeClosed(1, frameCount)
                .mapToObj(Frame::from)
                .collect(Collectors.toList());
    }

    public static FrameSet from(String name) {
        return new FrameSet(name, FRAME_COUNT);
    }

    public static FrameSet of(String name, int frameCount) {
        return new FrameSet(name, frameCount);
    }

    public boolean isPlaying() {
        return frames.stream()
                .anyMatch(Frame::hasNextTurn);
    }

    public void record(int hitCount) {
        frames.stream()
                .filter(Frame::hasNextTurn)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("게임이 끝났습니다."))
                .record(hitCount);
    }
}
