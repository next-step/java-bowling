package bowling.domain;

import bowling.dto.PlayerDto;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final List<NormalFrame> frames;

    private Player(String name) {
        this.name = name;
        frames = new ArrayList<>();
    }

    public static Player of(String name) {
        if (name.length() > 3) {
            throw new IllegalArgumentException(String.format("create Player fail. name length must be under 4 characters, name = %s", name));
        }

        return new Player(name);
    }


    public int getCurrentFrameNumber() {
        if (isCurrentFrameDone()) {
            return frames.size() + 1;
        }
        return frames.size();
    }

    public PlayerDto shot(int shot) {
        if (isCurrentFrameDone()) {
            frames.add(NormalFrame.init());
        }
        frames.get(frames.size() - 1).shot(shot);
        return new PlayerDto(name, frames);
    }

    private boolean isCurrentFrameDone() {
        return frames.isEmpty() || frames.get(frames.size() - 1).isFrameClosed();
    }
}
