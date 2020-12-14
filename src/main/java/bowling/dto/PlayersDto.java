package bowling.dto;

import java.util.List;

public class PlayersDto {
    private final List<PlayerDto> players;

    public PlayersDto(List<PlayerDto> players) {
        this.players = players;
    }

    public List<PlayerDto> getPlayers() {
        return players;
    }
}
