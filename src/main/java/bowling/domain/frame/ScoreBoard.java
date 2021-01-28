package bowling.domain.frame;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreBoard {

    private static final int lastFrameNumber = 10;

    private final List<Frame> frames;

    public ScoreBoard() {
        this.frames = new LinkedList<>();
        frames.add(Frame.createInitialFrame());
    }

    public void record(int downedPin) {
        if (getLatestFrame().isEnd()) {
            renewFrame();
        }

        getLatestFrame().record(downedPin);
    }

    private Frame getLatestFrame() {
        return frames.get(frames.size() - 1);
    }

    private void renewFrame() {
        if (frames.size() == lastFrameNumber - 1) {
            frames.add(new LastFrame());
            return;
        }

        frames.add(Frame.createInitialFrame());
    }

    public int getCurrentFrameNumber() {
        if (getLatestFrame().isEnd()) {
            return frames.size() + 1;
        }

        return frames.size();
    }

    public boolean isEnd() {
        return frames.size() == lastFrameNumber && getLatestFrame().isEnd();
    }

    public List<String> getDescriptions() {
        return frames.stream()
                .map(Frame::getDescriptionForm)
                .collect(Collectors.toList());
    }

    public List<Integer> getAccumulatedScoresOfFrames() {
        // 프레임에서 스코어를 가져오고, 누적 값을 반환한다.
        List<Integer> accumulatedScores = new ArrayList<>();

        if (frames.size() == 0) {
            return accumulatedScores;
        }

        return accumulatedScores;
    }
}
