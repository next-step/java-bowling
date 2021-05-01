package bowling.dto;

import java.util.List;

public class PlayersDto {

    private final List<PlayerDto> playerDtoList;

    public PlayersDto(List<PlayerDto> playerDtoList) {
        this.playerDtoList = playerDtoList;
    }

    public List<PlayerDto> getPlayerDtoList() {
        return playerDtoList;
    }

}
