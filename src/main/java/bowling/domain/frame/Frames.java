package bowling.domain.frame;

import bowling.domain.score.Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Frames {

    private static final int FRAME_SIZE = 10;

    private List<Frame> frames;

    Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames create() {
        List<Frame> frames = new ArrayList<>();
        frames.add(NormalFrame.createFirst());

        for (int i = 1; i < FRAME_SIZE; i++) {
            Frame frame = frames.get(i - 1);
            frames.add(frame.createNext(isNextLast(i)));
        }

        return new Frames(frames);
    }

    private static boolean isNextLast(int index) {
        return index == FRAME_SIZE - 1;
    }

    public boolean canAddMoreScore() {
        return frames.stream()
                .anyMatch(Frame::canAddMoreScore);
    }

    public void addScore(Score score) {
        Frame frame = frames.stream()
                .filter(Frame::canAddMoreScore)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("더 이상 투구 점수를 입력할 수 없습니다"));

        frame.addScore(score);
    }

    public Optional<Integer> getCurrentFrameIndex() {
        return frames.stream()
                .filter(Frame::canAddMoreScore)
                .findFirst()
                .map(Frame::getIndex);
    }

    public boolean isAllFrameFinished() {
        return frames.stream()
                .noneMatch(Frame::canAddMoreScore);
    }

    public List<Frame> getContent() {
        return Collections.unmodifiableList(frames);
    }
}
