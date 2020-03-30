package bowling.domain.frame;

import bowling.domain.player.Player;
import bowling.domain.state.State;

import java.util.LinkedList;

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
        if (preFrame.isFinish()) {
            frames.addLast(getFrame(preFrame, pins));
            return;
        }
        preFrame.bowl(pins);
    }

    private Frame getFrame(Frame preFrame, int pins) {
        Frame frame = new Frame(preFrame.getFrameNumber() + 1);
        if (frame.getFrameNumber() == 10) {
            frame.bowlByFinal(pins);
            return frame;
        }
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
        State firstState = firstFrame.getState();
        Score firstScore = firstState.getScore();

        for (Frame frame : frames) {
            if (!firstScore.isCalculation()) {
                firstScore = frame.calculateByBeforeScore(firstScore);
                firstState.renewScore(firstScore);
                firstFrame.updateState(firstState);
            }
        }
        return firstFrame;
    }

    public String getName() {
        return player.getName();
    }

    public int getFrameNumber() {
        return frames.getLast().getFrameNumber();
    }

    public LinkedList<Frame> getFrames() {
        return new LinkedList<>(frames);
    }
}
