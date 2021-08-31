package bowling.step2.domain;

import java.util.List;
import java.util.stream.Collectors;

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

    public boolean frameFinished() {
        return frameGroup.frameFinished();
    }

    public void nextFrame() {
        frameGroup.nextFrame();
    }

    public boolean isAbleToPitch() {
        return frameGroup.lastFrame()
                .isAbleToPitch();
    }

    public List<String> getResult() {
        return frameGroup.current()
                .stream()
                .map(Frame::current)
                .collect(Collectors.toList());
    }
}
