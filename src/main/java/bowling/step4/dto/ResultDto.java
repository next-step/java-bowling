package bowling.step4.dto;

import bowling.step4.domain.Player;

import java.util.ArrayList;
import java.util.List;

public class ResultDto {

    public final List<PlayerDto> playerDtoList;

    public ResultDto(List<PlayerDto> playerDtoList) {
        this.playerDtoList = playerDtoList;
    }

    public static ResultDto from(List<Player> players) {
        List<PlayerDto> playerDtoList = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            playerDtoList.add(new PlayerDto(players.get(i).name(), FramesDto.from(players.get(i).frames())));
        }
        return new ResultDto(playerDtoList);
    }
}
