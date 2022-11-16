package bowling.domain;

import java.util.List;

import bowling.domain.dto.BowlingGameFrameRecord;
import bowling.domain.frame.Frames;

public class BowlingGame {
    private final Frames frames;

    public BowlingGame() {
        frames = Frames.init();
    }

    public void bowl(int falledPins) {
        frames.bowl(falledPins);

        if (frames.isCurrentFrameEnded() && frames.isGamePlayable()) {
            frames.createNextFrame();
        }
    }

    public List<BowlingGameFrameRecord> createFrameRecords() {
        return frames.createFrameRecords();
    }

    public boolean isGamePlayable() {
        return frames.isGamePlayable();
    }

    public int getCurrentFrameNumber() {
        return frames.getCurrentFrameNumber();
    }
}
