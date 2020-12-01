package bowling.dto;

import java.util.Map;

public class GameDto {
    private final Map<PlayerDto, PlayerStatusDto> game;

    public GameDto(Map<PlayerDto, PlayerStatusDto> game) {
        this.game = game;
    }

    public Map<PlayerDto, PlayerStatusDto> getGame() {
        return game;
    }
}
