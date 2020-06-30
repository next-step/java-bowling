package bowling.domain.frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Frames {

    private static final int MIN_FRAME_NUMBER = 0;
    private static final int MAX_FRAME_NUMBER = 10;

    private final List<Frame> frames = new LinkedList<>();
    private final List<Integer> points = new ArrayList<>();
    private int frameIndex = 0;

    private Frames()  {
        validateFrameIndex(frameIndex);
        frames.add(NormalFrame.create());
    }

    public static Frames create() {
        return new Frames();
    }

    public Frames roll(Point point) {
        Frame frame = frames.get(frameIndex).bowl(point);
        updateFrame(frame);
        updatePoint(frame);
        return this;
    }

    private void updatePoint(Frame nowFrame) {
        int framePoint = nowFrame.getFramePoint();

        if (frameIndex > 0) { // 스페어나 스트라이크 있을 경우
            Frame previousFrame = frames.get(frameIndex - 1);

            if (previousFrame.getScores().contains("X")) { // 이전이 스트라이크일 경우
                if (frameIndex > 1) {
                    Frame earlierFrame = frames.get(frameIndex - 2);
                    if (earlierFrame.getScores().contains("X")) { // 그 이전도 스트라이크 일 경우
                        int sumStrikePoint = earlierFrame.getFramePoint() + previousFrame.getFramePoint() + framePoint;
                        points.remove(frameIndex);
                        points.remove(frameIndex - 1);
                        points.remove(frameIndex - 2);
                        points.add(sumStrikePoint);
                        int strikePoint = previousFrame.getFramePoint() + nowFrame.getFramePoint();
                        points.add(strikePoint);
                        points.add(framePoint);
                        return;
                    }
                }

                int strikePoint = previousFrame.getFramePoint() + nowFrame.getFramePoint();
                points.remove(frameIndex);
                points.remove(frameIndex - 1);
                points.add(strikePoint);
                points.add(framePoint);
                return;
            }

            if (previousFrame.getScores().contains("/")) {
                int sparePoint = previousFrame.getFramePoint() + framePoint;
                points.remove(frameIndex);
                points.remove(frameIndex - 1);
                points.add(sparePoint);
                points.add(framePoint);
                return;
            }
        }
        points.add(framePoint);
    }

    private void updateFrame(Frame frame) {
        frames.remove(frameIndex);
        frames.add(frame);
    }

    public void next() {
        if(frames.get(frameIndex).isLastPitch()) {
            frames.add(createNextFrame());
            points.add(0);
            frameIndex++;
        }
    }

    private Frame createNextFrame() {
        if (isBeforeLastIndex()) {
            return FinalFrame.create();
        }
        return NormalFrame.create();
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }

    public List<Integer> getPoints() {
        return Collections.unmodifiableList(points);
    }

    public boolean isLastFrame() {
        return frameIndex == MAX_FRAME_NUMBER;
    }

    public int getFrameIndex() {
        return frameIndex;
    }

    public int size() {
        return frames.size();
    }

    private boolean isBeforeLastIndex() {
        return frameIndex == 8;
    }

    private void validateFrameIndex(int frameIndex) {
        if (frameIndex < MIN_FRAME_NUMBER) {
            throw new IllegalArgumentException("프레임은 0보다 작을 수 없습니다.");
        }

        if (frameIndex > MAX_FRAME_NUMBER) {
            throw new IllegalArgumentException("프레임은 10보다 클 수 없습니다.");
        }

        if (frameIndex > frames.size()) {
            throw new IllegalArgumentException("index는 frames의 크기보다 클 수 없습니다.");
        }
    }
}

