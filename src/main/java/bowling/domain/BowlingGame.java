package bowling.domain;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.frame.Score;

public class BowlingGame {
    private final List<Frame> frames;

    public BowlingGame() {
        frames = new ArrayList<>(List.of(new NormalFrame(Frame.START_FRAME)));
    }

    public void bowl(int falledPins) {
        getCurrentFrame().bowl(falledPins);

        if (isCurrentFrameEnded() && isGamePlayable()) {
            frames.add(getCurrentFrame().createNextFrame());
        }
    }

    public List<BowlingGameFrameRecord> createFrameRecords() {
        List<BowlingGameFrameRecord> frameRecords = frames.stream()
            .map(Frame::createFrameRecord)
            .collect(toList());

        IntStream.range(frames.size(), Frame.LAST_FRAME)
            .forEach(i -> {
                frameRecords.add(new BowlingGameFrameRecord(Score.needToMoreBowl(), List.of()));
            });

        return frameRecords;
    }

    public boolean isGamePlayable() {
        return !(frames.size() == Frame.LAST_FRAME && isCurrentFrameEnded());
    }

    public int getCurrentFrameNumber() {
        return getCurrentFrame().getFrameNumber();
    }

    private Frame getCurrentFrame() {
        return frames.get(frames.size() - 1);
    }

    private boolean isCurrentFrameEnded() {
        return getCurrentFrame().isFrameFinish();
    }
}
