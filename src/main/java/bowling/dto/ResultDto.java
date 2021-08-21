package bowling.dto;

import java.util.List;

public class ResultDto {
    private PlayerDto player;
    private StatesDto states;

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
}
