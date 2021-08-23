package bowling.dto;

import java.util.ArrayList;
import java.util.List;

public class ResultDto {
    private final PlayerDto player;
    private final List<StateDtos> states;

    private ResultDto(final PlayerDto player, final List<StateDtos> states) {
        this.player = player;
        this.states = states;
    }

    public static ResultDto of(final PlayerDto player, final List<StateDtos> frames) {
        return new ResultDto(player, frames);
    }

    public String getName() {
        return player.getName();
    }

    public List<StateDtos> getStates() {
        return new ArrayList<>(states);
    }

    public int size() {
        return states.size();
    }
}
