package bowling.domain;

import bowling.domain.bonusPointFrame.BonusPointFrame;
import bowling.domain.bonusPointFrame.BonusPointFrames;
import bowling.domain.frame.Frames;

public class Bowling implements Playable {

    private static final int NO_BONUS = 0;

    private Frames frames;
    private BonusPointFrames bonusPointFrames;

    public Bowling() {
        frames = new Frames();
        bonusPointFrames = new BonusPointFrames();
    }

    @Override
    public void play(int point) {
        frames.moveFrameIfNeeded();
        frames.bowl(point);
        updateBonusFrame();
    }

    private void updateBonusFrame() {
        int bonusCount = frames.currentFrameBonus();
        if (bonusCount == NO_BONUS) {
            return;
        }
        bonusPointFrames.addBonusPointFrame(new BonusPointFrame(bonusCount, frames.currentFrame()));
    }

    @Override
    public boolean isEnd() {
        return frames.isEnd();
    }
}
