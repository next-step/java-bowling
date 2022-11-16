package bowling.domain.frame;

import static bowling.domain.frame.Frame.*;
import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import bowling.domain.dto.BowlingGameFrameRecord;

public class Frames {
    private static final String INVALID_FRAME_NUMBER_EXCEPTION_MESSAGE = "허용되지 않는 프레임 숫자 입니다.";

    private final List<Frame> frames;

    public static Frame createNextFrame(int frameNumber) {
        if (frameNumber >= LAST_FRAME) {
            throw new IllegalArgumentException(INVALID_FRAME_NUMBER_EXCEPTION_MESSAGE);
        }

        if (frameNumber + 1 == LAST_FRAME) {
            return new LastFrame();
        }

        return new NormalFrame(frameNumber + 1);
    }

    public static Frames init() {
        return new Frames(new ArrayList<>(List.of(new NormalFrame(START_FRAME))));
    }

    private Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public void bowl(int falledPins) {
        getCurrentFrame().bowl(falledPins);
    }

    public void createNextFrame() {
        frames.add(getCurrentFrame().createNextFrame());
    }

    public List<BowlingGameFrameRecord> createFrameRecords() {
        List<BowlingGameFrameRecord> frameRecords = frames.stream()
            .map(Frame::createFrameRecord)
            .collect(toList());

        IntStream.range(frames.size(), LAST_FRAME)
            .forEach(i -> frameRecords.add(new BowlingGameFrameRecord(Score.needToMoreBowl(), List.of())));

        return frameRecords;
    }

    public boolean isCurrentFrameEnded() {
        return getCurrentFrame().isFrameFinish();
    }

    public int getCurrentFrameNumber() {
        return getCurrentFrame().getFrameNumber();
    }

    public boolean isGamePlayable() {
        return !(frames.size() == LAST_FRAME && isCurrentFrameEnded());
    }

    private Frame getCurrentFrame() {
        return frames.get(frames.size() - 1);
    }
}
