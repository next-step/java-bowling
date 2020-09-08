package bowling.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FrameSet {
    private static final int FRAME_COUNT = 10;

    private Bowler bowler;
    private List<Frame> frames;

    private FrameSet(String name) {
        bowler = Bowler.from(name);
        frames = IntStream.rangeClosed(1, FRAME_COUNT)
                .mapToObj(stage -> new Frame(stage))
                .collect(Collectors.toList());
    }

    public static FrameSet from(String name) {
        return new FrameSet(name);
    }

    public boolean isPlaying() {
        return frames.stream()
                .filter(frame -> frame.hasNextTurn())
                .findFirst()
                .isPresent();
    }

    public void record(int hitCount) {
        frames.stream()
                .filter(frame -> frame.hasNextTurn())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("게임이 끝났습니다."))
                .record(hitCount);
    }
}
