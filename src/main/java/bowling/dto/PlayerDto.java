package bowling.dto;

import bowling.domain.PlayerName;
import bowling.domain.engine.frame.Frames;

public class PlayerDto {

    private final String playerName;
    private final FramesDto framesDto;

    private PlayerDto(String playerName, FramesDto framesDto) {
        this.playerName = playerName;
        this.framesDto = framesDto;
    }

    public static PlayerDto of(PlayerName playerName, Frames frames) {
        return new PlayerDto(playerName.export(), frames.export());
    }

    public String getPlayerName() {
        return playerName;
    }

    public FramesDto getFramesDto() {
        return framesDto;
    }
}
