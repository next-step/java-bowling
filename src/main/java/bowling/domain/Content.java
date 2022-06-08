package bowling.domain;

import java.util.Objects;

public class Content {

    public static final int LAST_FRAME_NO = 10;
    private static final int INITIAL_FRAME_NO = 1;
    private static final int NEXT_FRAME_NO = 1;

    private final int frameNo;
    private final Score lastScore;

    private Content(int frameNo, Score lastScore) {
        this.frameNo = frameNo;
        this.lastScore = lastScore;
    }

    public static Content initialize() {
        return new Content(INITIAL_FRAME_NO, null);
    }

    public Content next(Score lastScore) {
        return new Content(nextFrameNo(), lastScore);
    }

    private int nextFrameNo() {
        return frameNo + NEXT_FRAME_NO;
    }

    public boolean isNextFrameNoLast() {
        return nextFrameNo() == LAST_FRAME_NO;
    }

    public int frameNo() {
        return frameNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Content content = (Content) o;
        return frameNo == content.frameNo && lastScore == content.lastScore;
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNo, lastScore);
    }
}
