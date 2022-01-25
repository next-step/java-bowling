package bowling.domain;

import bowling.domain.frame.DefaultFrame;
import bowling.domain.frame.Frames;

import static bowling.domain.frame.Frames.BOARD_MAX_SIZE;

public class Game {
    private final Frames frames;

    private int frameNum = 1;

    public Game(Frames frames) {
        this.frames = frames;
    }

    public void init() {
        frames.init();
    }

    public boolean isGameOver() {
        return frameNum > BOARD_MAX_SIZE;
    }

    public Frames getBoard() {
        return frames;
    }

    public void playGame(KnockedPins knockedPins) {
        DefaultFrame recentDefaultFrame = frames.getRecentFrame(frameNum - 1);

        if (!recentDefaultFrame.isLastFrame()) {
            playNormalGame(recentDefaultFrame, knockedPins);
        }
        if (recentDefaultFrame.isLastFrame()) {

            playLastGame(recentDefaultFrame, knockedPins);
        }
    }

    private void playNormalGame(DefaultFrame recentDefaultFrame, KnockedPins knockedPins) {
        if (!recentDefaultFrame.hasDoneFirstPitch()) {
            recentDefaultFrame.bowl(knockedPins, 1);
            if (knockedPins.isStrike()) {
                frameNum++;
            }
            return;
        }
        if (recentDefaultFrame.hasDoneFirstPitch()) {
            recentDefaultFrame.bowl(knockedPins, 2);
            frameNum++;
        }
    }

    private void playLastGame(DefaultFrame recentDefaultFrame, KnockedPins knockedPins) {

        if (recentDefaultFrame.hasDoneFirstPitch() && !recentDefaultFrame.hasDoneSecondPitch()) {
            recentDefaultFrame.bowl(knockedPins, 2);
            if (!recentDefaultFrame.isSpare(recentDefaultFrame.getFirstScore(), recentDefaultFrame.getSecondScore()) && !recentDefaultFrame.isStrike()) {
                frameNum++;
                return;
            }
            return;
        }
        if (recentDefaultFrame.hasDoneFirstPitch() && recentDefaultFrame.hasDoneSecondPitch()) {
            recentDefaultFrame.bowl(knockedPins, 3);
            frameNum++;
        }
        if (recentDefaultFrame.isEmpty()) {
            recentDefaultFrame.bowl(knockedPins, 1);
            return;
        }
    }

    public int getFrameNum() {
        return this.frameNum;
    }
}


