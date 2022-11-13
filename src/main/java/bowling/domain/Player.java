package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.dto.PlayerDto;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final Frame frame;

    public Player(String name) {
        if (!name.matches("^[a-zA-Z]{3}$")) {
            throw new IllegalArgumentException("이름은 영문자로 세글자이어야 합니다.");
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
