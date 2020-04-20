package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.score.Score;

import java.util.Optional;

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

    private Frame findFrame(int frameNumber) {
        Frame frame = firstFrame;

        for (int i = 0; i < frameNumber - 1; i++) {
            frame = Optional.ofNullable(frame.getNext())
                    .orElse(null);
        }

        return frame;
    }

    public Score getTotalScore(int frameNumber) {
        Score result = new Score();
        for (int i = 1; i <= frameNumber; i++) {
            Score frameScore = getFrameScore(i);
            result = new Score(result.getScore() + frameScore.getScore());
        }
        return result;
    }

    private Score getFrameScore(int frameNumber) {
        Frame frame = findFrame(frameNumber);

        if (frame == null) {
            return new Score();
        }

        Score score = frame.getCurrentScore();

        while (score.canAddNextScore() && frame.getNext() != null) {
            frame = frame.getNext();
            score = frame.getCalculateScore(score);
        }

        return score;
    }
}
