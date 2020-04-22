package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.score.Score;

public class BowlingGame {

    private final Player player;
    private final Frame firstFrame;

    public BowlingGame(final Player player) {
        this.player = player;
        this.firstFrame = new NormalFrame();
    }

    private Frame findLastFrame() {
        return firstFrame.findLast();
    }

    public void play(final int pinCount) {
        Frame last = findLastFrame();

        if (last.isFinish()) {
            last.createNext();
        }

        last.findLast().bowl(pinCount);
    }

    public String getPlayerName() {
        return player.getName();
    }

    public boolean isFinish() {
        return findLastFrame().isEnd();
    }

    public boolean isLastFrameFinish() {
        return findLastFrame().isFinish();
    }

    public Frame getCurrentFrame() {
        return findLastFrame();
    }

    public Frame getFirstFrame() {
        return firstFrame;
    }

    public int getFrameSize() {
        int count = 1;
        Frame frame = firstFrame;

        while (frame.getNext() != null) {
            frame = frame.getNext();
            count++;
        }

        return count + 1;
    }

    public Score getTotalScore(int frameNumber) {
        return firstFrame.getTotalScore(frameNumber);
    }
}
