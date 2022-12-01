package bowling.domain.frame;

import bowling.domain.Score;

import java.util.List;
import java.util.Optional;

public class Bowling {

    private static final int END_FRAME_INDEX = 10;
    private int currentFrameIndex = 0;
    private final List<Frame> values;

    private Bowling(List<Frame> values) {
        this.values = values;
    }

    public void bowling(int count) {
        Frame frame = getCurrentFrame();
        frame.bowl(count);

        if (frame.isFinish()) {
            currentFrameIndex++;
        }
    }

    public boolean isNotEnd() {
        return currentFrameIndex != END_FRAME_INDEX;
    }

    private Frame getCurrentFrame() {
        return values.get(currentFrameIndex);
    }

    public Frame get(int index) {
        return values.get(index);
    }

    public int getCurrentFrameIndex() {
        return currentFrameIndex;
    }

    public int size() {
        return values.size();
    }

    public boolean isEmpty(int index) {
        return false;
    }

    public Optional<Score> getScore(Integer index) {
//        return Optional.ofNullable(values.get(index))
//                .filter(Frame::isEnd)
//                .map(Frame::getScore)
//                .orElseGet(() -> Optional.empty());

        return null;
    }
}
