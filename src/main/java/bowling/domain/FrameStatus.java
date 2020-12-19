package bowling.domain;

public class FrameStatus {
    protected final Integer first;
    protected final Integer second;
    protected final Integer third;

    public FrameStatus(Integer first, Integer second, Integer third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public FrameStatus(FrameStatus frameStatus) {
        this(frameStatus.first, frameStatus.second, frameStatus.third);
    }
}
