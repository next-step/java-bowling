package bowling.domain;

import bowling.domain.frame.Frame;

import java.util.List;

public class PlayerDto {
    private final String name;
    private final List<Frame> frame;

    public PlayerDto(String name, List<Frame> frame) {
        this.name = name;
        this.frame = frame;
    }

    public String name() {
        return name;
    }

    public List<Frame> frame() {
        return frame;
    }
}
