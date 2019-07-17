package domain.frame;

import java.util.Objects;

public class FrameIndex {

    public final static int START_FRAME = 0;
    public final static int TOTAL_FRAME = 10;
    public final static int LAST_NORMAL_FRAME = 9;

    private Integer frameCount;

    private FrameIndex(Integer frameCount) {
        this.frameCount = frameCount;
    }

    public static FrameIndex of(Integer frameCount) {
        return new FrameIndex(frameCount);
    }

    public FrameIndex newInstance() {
        return new FrameIndex(frameCount);
    }

    public FrameIndex next() {
        int nextFrameCount = frameCount + 1;
        if (TOTAL_FRAME < nextFrameCount) {
            throw new RuntimeException("10 프레임 이상으로 진행 할 수 없습니다. 현재 프레임 : " + nextFrameCount);
        }
        return FrameIndex.of(nextFrameCount);
    }

    public Integer value() {
        return frameCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrameIndex that = (FrameIndex) o;
        return Objects.equals(frameCount, that.frameCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameCount);
    }

    @Override
    public String toString() {
        return "FrameIndex{" +
                "frameCount=" + frameCount +
                '}';
    }

    public boolean isStop() {
        return frameCount == TOTAL_FRAME;
    }
}
