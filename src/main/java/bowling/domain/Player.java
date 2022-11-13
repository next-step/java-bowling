package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final Frame frame;

    public Player(String name) {
        if (!name.matches("^[a-zA-Z]{3}$")) {
            throw new IllegalArgumentException("이름이 영문자가 아닙니다.");
        }
        if (name.length() != 3) {
            throw new IllegalArgumentException("이름이 3글자가 아닙니다.");
        }
        this.name = name;
        this.frame = Frame.frame();
    }

    public int frameOrderToThrow() {
        return frame.orderToThrow();
    }

    public void throwBall(int knockDownCount) {
        frame.addChances(knockDownCount);
    }

    public PlayerDto status() {
        List<Frame> frames = new ArrayList<>();
        Frame frame = this.frame;
        while (frame.getClass() == NormalFrame.class) {
            frames.add(frame);
            frame = ((NormalFrame) frame).nextFrame();
        }
        frames.add(frame);
        return new PlayerDto(name, frames);
    }

}
