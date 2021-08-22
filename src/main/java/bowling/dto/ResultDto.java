package bowling.dto;

import java.util.List;

public class ResultDto {
    private final PlayerDto player;
    private final StateDtos states;

    private ResultDto(final PlayerDto player, final StateDtos states) {
        this.player = player;
        this.states = states;
    }

    public static ResultDto of(final PlayerDto player, final StateDtos frames) {
        return new ResultDto(player, frames);
    }

    public String getName() {
        return player.getName();
    }

    public List<StateDto> getStates() {
        return states.getStates();
    }

    public int size() {
        return getStates().size();
    }
}
