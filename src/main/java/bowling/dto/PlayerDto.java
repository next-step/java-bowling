package bowling.dto;

import bowling.domain.Player;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerDto {

    private String name;

    private List<FrameDto> frames;

    public PlayerDto(Player player) {
        this.name = player.name();
        this.frames=player.frames().stream()
        .map(frame -> new FrameDto(frame))
        .collect(Collectors.toList());
    }

    public String name() {
        return name;
    }

    public List<FrameDto> frames() {
        return frames;
    }
}
