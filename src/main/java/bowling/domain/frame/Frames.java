package bowling.domain.frame;

import static bowling.domain.frame.Frame.*;
import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import bowling.domain.dto.BowlingGameFrameRecord;

public class Frames {
    private final List<Frame> frames;

    public static Frame createNextFrame(int frameNumber) {
        if (frameNumber == LAST_FRAME) {
            throw new UnsupportedOperationException();
        }

        if (frameNumber + 1 == LAST_FRAME) {
            return new LastFrame();
        }

        return new NormalFrame(frameNumber + 1);
    }

    public Frames() {
        this.frames = new ArrayList<>(List.of(new NormalFrame(Frame.START_FRAME)));
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
            .forEach(i -> {
                frameRecords.add(new BowlingGameFrameRecord(Score.needToMoreBowl(), List.of()));
            });

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
