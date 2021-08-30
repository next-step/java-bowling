package bowling.step2.domain;

public class Lane {
    private final String name;

    private final FrameGroup frameGroup;

    private Lane(String name) {
        this.name = name;
        this.frameGroup = FrameGroup.of();
    }

    public static Lane of(String name) {
        return new Lane(name);
    }

    public void pitch(int count) {
        frameGroup.pitch(count);
    }

    public void nextFrame() {
        frameGroup.nextFrame();
    }
}
