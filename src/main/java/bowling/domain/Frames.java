package bowling.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Frames {
    public static final int COUNT = 10;

    private List<Frame> frames;

    private Frames(int frameCount) {
        if (frameCount != COUNT) {
            throw new IllegalArgumentException(String.format("프레임 수는 %s보다 많거나 작을 수 없습니다. [%s]", COUNT, frameCount));
        }

        frames = IntStream.rangeClosed(1, frameCount)
                .mapToObj(Frame::from)
                .collect(Collectors.toList());
    }


    public static Frames from(int frameCount) {
        return new Frames(frameCount);
    }

    public static Frames create() {
        return from(COUNT);
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

    public int getPlayStage() {
        return frames.stream()
                .filter(Frame::hasNextTurn)
                .findFirst()
                .get()
                .getStage();
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }
}
