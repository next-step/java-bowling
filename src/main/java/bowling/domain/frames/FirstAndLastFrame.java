package bowling.domain.frames;

public class FirstAndLastFrame {
    private final FrameImpl first;
    private final FrameImpl last;

    private FirstAndLastFrame(FrameImpl first, FrameImpl last) {
        this.first = first;
        this.last = last;
    }

    public static FirstAndLastFrame of(FrameImpl first, FrameImpl last) {
        return new FirstAndLastFrame(first, last);
    }

    public FrameImpl getFirst() {
        return first;
    }

    public int getLastFrameIndex() {
        return last.index;
    }

    public boolean isEnd() {
        return last.isEnd();
    }
}
