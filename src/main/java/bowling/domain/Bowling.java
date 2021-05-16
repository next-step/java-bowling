package bowling.domain;

import bowling.domain.bonusPointFrame.BonusPointFrame;
import bowling.domain.bonusPointFrame.BonusPointFrames;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;

public class Bowling {

    private Frames frames;
    private BonusPointFrames bonusPointFrames;

    public Bowling() {
        frames = new Frames();
        bonusPointFrames = new BonusPointFrames();
    }

    public void play(int point) {
        frames.moveFrameIfNeeded();
        frames.bowl(point);
        updateBonusFrame();
        bonusPointFrames.update();
        bonusPointFrames.addBonusPoints(point);
    }

    private void updateBonusFrame() {
        int bonusCount = frames.currentFrameBonus();
        Frame currentFrame = frames.currentFrame();
        bonusPointFrames.addBonusPointFrame(new BonusPointFrame(bonusCount, currentFrame));
    }

    public boolean isEnd() {
        return frames.isEnd();
    }

    public int closedScores() {
        return frames.closedFrames();
    }
}
