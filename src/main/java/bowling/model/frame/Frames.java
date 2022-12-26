package bowling.model.frame;

import bowling.model.Pin;
import bowling.model.Score;
import bowling.model.state.Ready;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Frames {

    private List<Frame> frames = new ArrayList<>();

    public Frames() {
        frames.add(NormalFrame.first());
    }

    public void bowl(Pin pin) {
        Frame frame = getCurrentFrame();
        frame.bowl(pin);
    }

    public Frame nextFrame() {
        Frame frame = getCurrentFrame();
        if (isFinished() && !frame.isFinalFrame()) {
            frames.add(frame.nextFrame());
        }
        return getCurrentFrame();
    }

    public boolean isCurrentFrameFinished() {
        return isFinished() || isReady();
    }

    private boolean isFinished() {
        return getCurrentFrame().isFinished();
    }

    public boolean isReady() {
        return getCurrentFrame().getCurrentState() instanceof Ready;
    }

    public boolean isGameOver() {
        Frame frame = getCurrentFrame();
        return frame.isFinished();
    }

    public int size() {
        return frames.size();
    }

    public Frame getCurrentFrame() {
        return frames.get(getCurrentIndex());
    }

    private int getCurrentIndex() {
        return frames.size() - 1;
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }

    public List<Integer> getSumScores() {
        List<Integer> scores = getScores();
        List<Integer> result = new ArrayList<>();

        int sumScore = 0;
        for (int score : scores) {
            sumScore += score;
            result.add(sumScore);
        }

        return result;
    }

    private List<Integer> getScores() {
        return frames.stream()
                .filter(Frame::isFinished)
                .map(Frame::getScore)
                .filter(Score::canCalculate)
                .map(Score::getScore)
                .collect(toList());
    }
}
