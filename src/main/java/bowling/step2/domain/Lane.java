package bowling.step2.domain;

import java.util.List;

public class Lane {
    private final String name;

    private final FrameGroup frameGroup;

    private final int nameLength = 3;

    public Lane(String name, FrameGroup frameGroup) {
        validateName(name);
        this.name = name;
        this.frameGroup = frameGroup;
    }

    public static Lane of(String name) {
        return new Lane(name, FrameGroup.of());
    }

    private void validateName(String name) {
        if (name.length() > nameLength) {
            throw new RuntimeException("이름의 길이는 최대 3을 넘을 수 없습니다.");
        }
    }

    public void pitch(int frameNo, TryNo tryNo, int count) {
        frameGroup.pitch(frameNo, tryNo, count);
    }

    public List<Frame> getScoreOfLane() {
        return frameGroup.getScoreOfFrameGroup();
    }

    public boolean isNotAbleToPitchAdditional() {
        return frameGroup.isNotAbleToPitchAdditional();
    }

    public String nameOfLane() {
        return name;
    }
}
