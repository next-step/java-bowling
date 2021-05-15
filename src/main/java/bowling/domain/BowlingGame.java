package bowling.domain;

import bowling.domain.frame.Frames;
import bowling.domain.frame.bonusNeededFrame.BonusNeededFrames;

public class BowlingGame implements Playable {
    private Frames frames;
    private BonusNeededFrames bonusNeededFrames;

    @Override
    public void play(Point point) {
        frames.bowl(point);
    }

    @Override
    public boolean isEnd() {
        return frames.isEnd();
    }
}
