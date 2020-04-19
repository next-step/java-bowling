package bowling.domain;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.score.Calculator;
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
        if (findLastFrame() instanceof FinalFrame && findLastFrame().isFinish()) {
            return true;
        }
        return false;
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

    public Score getFrameScore(int frameNumber) {
        Frame frame = findFrame(frameNumber);

        if (frame == null) {
            return null;
        }

        Calculator calculator = frame.getState().getCurrenteCalculator();

        if (calculator.canAddNextScore() && frame.getNext() != null) {
            calculator = frame.getNext().getState().getScoreCalculate(calculator);
        }

        if (calculator.canAddNextScore() && frame.getNext() == null) {
            return null;
        }

        return calculator.getScore();
    }
}
