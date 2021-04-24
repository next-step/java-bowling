package bowling.dto;

import bowling.domain.PlayerName;
import bowling.domain.engine.Bowling;

public class PlayerDto {

    private final String playerName;
    private final BowlingDto bowlingDto;

    private PlayerDto(String playerName, BowlingDto bowlingDto) {
        this.playerName = playerName;
        this.bowlingDto = bowlingDto;
    }

    public static PlayerDto of(PlayerName playerName, Bowling bowling) {
        return new PlayerDto(playerName.export(), bowling.export());
    }

    public String getPlayerName() {
        return playerName;
    }

    public BowlingDto getBowlingDto() {
        return bowlingDto;
    }
}
