package bowling.dto;

import bowling.domain.Player;
import bowling.domain.bowlingboard.BowlingBoard;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerBowlingBoardDto {

    private final List<PlayerDto> playerDtoList;
    private final List<BowlingBoardDto> bowlingBoardDtoList;

    private PlayerBowlingBoardDto(List<PlayerDto> playerDtoList, List<BowlingBoardDto> bowlingBoardDtoList) {
        this.playerDtoList = playerDtoList;
        this.bowlingBoardDtoList = bowlingBoardDtoList;
    }

    public static PlayerBowlingBoardDto of(List<Player> players, List<BowlingBoard> bowlingBoards) {
        List<PlayerDto> playerDtos = players.stream()
                .map(PlayerDto::of)
                .collect(Collectors.toList());

        List<BowlingBoardDto> bowlingBoardDtos = bowlingBoards.stream()
                .map(BowlingBoardDto::of)
                .collect(Collectors.toList());
        return new PlayerBowlingBoardDto(playerDtos, bowlingBoardDtos);
    }

    public static PlayerBowlingBoardDto of(List<Player> players) {
        return players.stream()
                .map(PlayerDto::of)
                .collect(Collectors.collectingAndThen(Collectors.toList(), playerDtos -> new PlayerBowlingBoardDto(playerDtos, new ArrayList<>())));
    }

    public PlayerDto getPlayerDto(int index) {
        return playerDtoList.get(index);
    }

    public BowlingBoardDto getBowlingBoardDto(int index) {
        return bowlingBoardDtoList.get(index);
    }

    public int size() {
        return bowlingBoardDtoList.size();
    }
}
