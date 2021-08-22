package bowling.dto;

import java.util.List;

public class ResultDto {
    private final PlayerDto player;
    private final StatesDto states;

    private ResultDto(final PlayerDto player, final StatesDto states) {
        this.player = player;
        this.states = states;
    }

    public static ResultDto of(final PlayerDto player, final StatesDto frames) {
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
