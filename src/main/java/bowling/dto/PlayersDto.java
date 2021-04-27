package bowling.dto;

import bowling.domain.Player;

import java.util.List;
import java.util.stream.Collectors;

public class PlayersDto {

    public final List<PlayerDto> playerDtos;

    private PlayersDto(List<PlayerDto> playerDtos) {
        this.playerDtos = playerDtos;
    }

    public static PlayersDto of(List<Player> players) {
        return players.stream()
                .map(PlayerDto::of)
                .collect(Collectors.collectingAndThen(Collectors.toList(), PlayersDto::new));
    }

    public int playerCount() {
        return playerDtos.size();
    }

    public List<PlayerDto> getPlayerDtos() {
        return playerDtos;
    }
}
