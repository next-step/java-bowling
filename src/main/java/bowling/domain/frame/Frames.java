package bowling.domain.frame;

import bowling.domain.state.State;

import java.util.LinkedList;

public class Frames {

    private LinkedList<Frame> frames;

    public Frames(LinkedList<Frame> frames) {
        this.frames = frames;
    }

    public void bowl(int pins) {
        Frame preFrame = frames.getLast();
        if (preFrame.isFinish()) {
            Frame frame = new Frame(preFrame.getFrameNumber() + 1);

            if (frame.getFrameNumber() == 10) {
                frame.bowlByFinal(pins);
                frames.addLast(frame);
                return;
            }
            frame.bowl(pins);
            frames.addLast(frame);
            return;
        }
        preFrame.bowl(pins);
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

    public LinkedList<Frame> getFrames() {
        return new LinkedList<>(frames);
    }
}
