package bowling.dto;

import bowling.domain.player.BowlingPlayer;

import java.util.List;

public class BowlingPlayerDto {
    private final String name;
    private final int currentFrameNumber; // 1 base
    private final List<StateDtos> states;

    private BowlingPlayerDto(BowlingPlayer player) {
        this.name = player.getName();
        this.states = player.getStates();
        this.currentFrameNumber = states.size();
    }

    public static BowlingPlayerDto from(BowlingPlayer player) {
        return new BowlingPlayerDto(player);
    }

    public String getName() {
        return name;
    }

    public int getCurrentFrameNumber() {
        return currentFrameNumber;
    }

    public List<StateDtos> getStates() {
        return states;
    }

}
