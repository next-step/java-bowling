package bowling.domain;

public class FrameResult {

    private final int hitCount;
    private final int playCount;

    public static FrameResult create(FrameSet frameSet) {
        return new FrameResult(frameSet);
    }

    private FrameResult(FrameSet frameSet) {
        this.hitCount = frameSet.getHitCount();
        this.playCount = frameSet.getPlayCount();
    }

    public int getHitCount() {
        return hitCount;
    }

    public int getPlayCount() {
        return playCount;
    }
}
