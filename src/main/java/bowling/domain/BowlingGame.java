package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.player.Player;
import bowling.domain.score.Score;

import java.util.Objects;

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

        if (isLastFrameFinish()) {
            last.createNext();
        }

        last.findLast().bowl(pinCount);
    }

    public boolean equalPlayer(Player player) {
        return this.player.equals(player);
    }

    public String getPlayerName() {
        return player.getName();
    }

    public boolean isEnd() {
        return findLastFrame().isEnd();
    }

    public boolean isLastFrameFinish() {
        return findLastFrame().isFinish();
    }

    public boolean isFrameFinish(final int frameNumber) {
        return firstFrame.isFrameFinish(frameNumber);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BowlingGame game = (BowlingGame) o;
        return Objects.equals(player, game.player) &&
                Objects.equals(firstFrame, game.firstFrame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, firstFrame);
    }
}
