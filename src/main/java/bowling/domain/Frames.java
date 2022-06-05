package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private final List<Frame> frames;

    public Frames(Frame frame) {
        this.frames = new ArrayList<>();
        frames.add(frame);
    }

    public Frame getFrame(int i) {
        if(i > this.frames.size()) {
            throw new IllegalArgumentException("프레임 사이즈보다 큰 숫자를 입력 했습니다.");
        }
        return frames.get(i - 1);
    }

    public void add(Frame frame) {
        this.frames.add(frame);
    }

}
