package bowling.dto;

import bowling.domain.PlayerName;
import bowling.domain.engine.Bowling;

public class PlayerDto {

    private final String playerName;
    private final FramesDto framesDto;

    private PlayerDto(String playerName, FramesDto framesDto) {
        this.playerName = playerName;
        this.framesDto = framesDto;
    }

    public static PlayerDto of(PlayerName playerName, Bowling bowling) {
        return new PlayerDto(playerName.export(), bowling.exportFrames());
    }

    public String getPlayerName() {
        return playerName;
    }

    public FramesDto getFramesDto() {
        return framesDto;
    }
}
