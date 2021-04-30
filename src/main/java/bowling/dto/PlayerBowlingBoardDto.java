package bowling.dto;

import bowling.domain.Player;
import bowling.domain.bowlingboard.PlayerBowlingBoards;

import java.util.List;

public class PlayerBowlingBoardDto {

    private final List<PlayerDto> playerDtoList;
    private final List<BowlingBoardDto> bowlingBoardDtoList;

    private PlayerBowlingBoardDto(List<PlayerDto> playerDtoList, List<BowlingBoardDto> bowlingBoardDtoList) {
        this.playerDtoList = playerDtoList;
        this.bowlingBoardDtoList = bowlingBoardDtoList;
    }

    public static PlayerBowlingBoardDto of(PlayerBowlingBoards playerBowlingBoards) {
        return new PlayerBowlingBoardDto(playerBowlingBoards.playerDtoList(), playerBowlingBoards.bowlingBoardDtoList());
    }

    public static PlayerBowlingBoardDto of(PlayerBowlingBoards playerBowlingBoards, Player player) {
        return new PlayerBowlingBoardDto(playerBowlingBoards.playerDtoList(player), playerBowlingBoards.bowlingBoardDtoList());
    }

    public PlayerDto getPlayerDto(int index) {
        return playerDtoList.get(index);
    }

    public BowlingBoardDto getBowlingBoardDto(int index) {
        return bowlingBoardDtoList.get(index);
    }

    public int size() {
        return playerDtoList.size();
    }
}
