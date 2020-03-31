package bowling.domain.frame;

import bowling.domain.player.Player;
import bowling.domain.state.State;

import java.util.LinkedList;
import java.util.Objects;

public class Frames {

    private LinkedList<Frame> frames;
    private Player player;

    public Frames(LinkedList<Frame> frames) {
        this.frames = frames;
    }

    public Frames(LinkedList<Frame> frames, Player player) {
        this.frames = frames;
        this.player = player;
    }

    public void bowl(int pins) {
        Frame preFrame = frames.getLast();
        if (preFrame.isFinalFrame()) {
            if (preFrame.isSpare() || preFrame.isStrike()) {
                preFrame.bowl(pins);
                return;
            }
        }
        if (preFrame.isFinish()) {
            frames.add(getFrame(preFrame, pins));
            return;
        }
        preFrame.bowl(pins);
    }

    private Frame getFrame(Frame preFrame, int pins) {
        Frame frame = new Frame(preFrame.getFrameNumber() + 1);
        frame.bowl(pins);
        return frame;
    }

    public LinkedList<Frame> getFramesByCalculationScore() {
        LinkedList<Frame> framesBeforeCalculation = getFrames();
        LinkedList<Frame> frames = new LinkedList<>();

        while (framesBeforeCalculation.size() != 0) {
            Frame frame = calculateScore(framesBeforeCalculation);
            frames.addLast(frame);
        }
        return frames;
    }

    private Frame calculateScore(LinkedList<Frame> frames) {
        Frame firstFrame = frames.removeFirst();
        Score firstScore = firstFrame.getScoreByState();

        for (Frame frame : frames) {
            if (Objects.nonNull(firstScore) && !firstScore.isCalculation()) {
                firstScore = frame.calculateByBeforeScore(firstScore);
                firstFrame.renewScore(firstScore);
            }
        }
        return firstFrame;
    }

    public boolean isEnd() {
        if (frames.getLast().isBonus()) {
            return true;
        }
        return frames.getLast().isMiss() && frames.size() == 10;
    }

    public String getName() {
        return player.getName();
    }

    public int getFrameNumber() {
        if (frames.getLast().isFinish()) {
            return frames.getLast().getFrameNumber() + 1;
        }
        return frames.getLast().getFrameNumber();
    }

    public LinkedList<Frame> getFrames() {
        return new LinkedList<>(frames);
    }
}
