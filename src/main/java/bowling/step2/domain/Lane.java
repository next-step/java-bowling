package bowling.step2.domain;

public class Lane {
    private final String name;

    private final FrameGroup frameGroup;

    public Lane(String name, FrameGroup frameGroup) {
        this.name = name;
        this.frameGroup = frameGroup;
    }

    public static Lane of(String name) {
        return new Lane(name, FrameGroup.of());
    }
}
