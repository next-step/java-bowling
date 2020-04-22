package bowling.dto;

import bowling.domain.Player;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerDto {
    private final String name;
    private final List<FrameDto> frames;


    public PlayerDto(Player player) {
        this.name = player.name();
        this.frames = player.frames()
                .getFrames()
                .stream()
                .map(FrameDto::new)
                .collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public List<FrameDto> getFrames() {
        return frames;
    }
}
