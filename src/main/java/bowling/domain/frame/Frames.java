package bowling.domain.frame;

import bowling.domain.Pin;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Frames {

    public static final int MAX_FRAME_COUNT = 10;
    private final List<Frame> frames;

    public Frames() {
        this.frames = new LinkedList<>();
    }

    public void bowl(int countOfPin) {
        if (isEnd()) {
            throw new IllegalArgumentException("프레임이 종료되었습니다.");
        }

        createFrame();
        getLastFrame().bowl(countOfPin);
        calculateScore(countOfPin);
    }

    private void calculateScore(int countOfPin) {
        for (Frame frame : frames) {
            frame.calculateScore(frames.size() - 1, countOfPin);
        }
    }

    private void createFrame() {
        if (frames.isEmpty()) {
            frames.add(NormalFrame.first());
            return;
        }

        if (!getLastFrame().isEnd()) {
            return;
        }

        Frame finalFrame = frames.size() > NormalFrame.MAX_NORMAL_FRAME_NUMBER ? new FinalFrame() : getLastFrame().next();
        frames.add(finalFrame);
    }

    public boolean isFinalFrame() {
        return frames.size() == MAX_FRAME_COUNT;
    }

    public boolean isEnd() {
        return  isFinalFrame() && getLastFrame().isEnd();
    }

    private Frame getLastFrame() {
        if (frames.isEmpty()) {
            throw new IllegalArgumentException("프레임이 비었습니다.");
        }

        return frames.get(frames.size() - 1);
    }

    public List<Integer> getScores() {
        return frames.stream()
                .filter(Frame::hasScore)
                .map(Frame::getScore)
                .collect(Collectors.toList());
    }

    public int getFrameNumber() {
        if (frames.isEmpty() || getLastFrame().isEnd()) {
            return frames.size() + 1;
        }

        return frames.size();
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public int size() {
        return frames.size();
    }
}
