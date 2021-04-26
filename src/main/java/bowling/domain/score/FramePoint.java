package bowling.domain.score;

public class FramePoint {

    private static final int INIT_POINT = 0;

    private final int framePoint;

    private FramePoint(int framePoint) {
        this.framePoint = framePoint;
    }

    public static FramePoint of(int framePoint) {
        return new FramePoint(framePoint);
    }

    public static FramePoint first() {
        return new FramePoint(INIT_POINT);
    }

    public FramePoint framePoint(int framePoint) {
        return new FramePoint(this.framePoint + framePoint);
    }

    public int toInt() {
        return framePoint;
    }

}
