package bowling.dto;

import bowling.domain.Players;

import java.util.List;
import java.util.stream.Collectors;

public class PlayersDto {
    private final List<PlayerDto> players;

    public PlayersDto(Players players) {
        this.players = players.getPlayers()
                .stream()
                .map(PlayerDto::new)
                .collect(Collectors.toList());
    }

    public List<PlayerDto> getPlayers() {
        return players;
    }
}
