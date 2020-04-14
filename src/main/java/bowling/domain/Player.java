package bowling.domain;

import bowling.dto.FramesDto;
import bowling.dto.PlayerDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Player {
    private static final int MAX_FRAMES = 10;
    private final String name;
    private final List<Frame> frames;

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

    public void shot(int shot) {
        if (isCurrentFrameDone()) {
            frames.add(createAddFrame());
        }
        frames.get(frames.size() - 1).shot(shot);
    }

    private boolean isCurrentFrameDone() {
        return frames.isEmpty() || frames.get(frames.size() - 1).isFrameClosed();
    }

    private Frame createAddFrame() {
        if(frames.size()==9){
            return FinalFrame.of();
        }
        return NormalFrame.init();
    }

    public PlayerDto getDto() {
        return new PlayerDto(name,
                new FramesDto(frames.stream()
                        .map(Frame::getDto)
                        .collect(Collectors.toList())));
    }
}
