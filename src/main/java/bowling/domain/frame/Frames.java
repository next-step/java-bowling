package bowling.domain.frame;

import org.springframework.util.CollectionUtils;

import java.util.List;

public class Frames {
    private final List<Frame> frames;

    public Frames(List<Frame> frames) {
        validate(frames);
        this.frames = frames;
    }

    private void validate(List<Frame> frames) {
        if (CollectionUtils.isEmpty(frames)) {
            throw new IllegalArgumentException("frames는 빈 값 일 수 없습니다.");
        }
    }

    public static Frames initialize() {
        return new Frames(List.of(Frame.initialize()));
    }
}
