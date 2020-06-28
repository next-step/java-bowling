package bowling.domain.frame;

import bowling.domain.score.Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
            frames.add(frame.createNext(i == FRAME_SIZE - 1));
        }

        return new Frames(frames);
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

    public int getCurrentFrameIndex() {
        return frames.stream()
                .filter(Frame::canAddMoreScore)
                .findFirst()
                .map(Frame::getIndex)
                .orElseThrow(() -> new IllegalStateException("진행 중인 프레임이 없습니다"));
    }

    public List<Frame> getContent() {
        return Collections.unmodifiableList(frames);
    }
}
