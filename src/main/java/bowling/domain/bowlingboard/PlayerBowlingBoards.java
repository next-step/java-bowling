package bowling.domain.bowlingboard;

import bowling.domain.Player;
import bowling.domain.frame.round.Round;
import bowling.dto.BowlingBoardDto;
import bowling.dto.PlayerDto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class PlayerBowlingBoards {

    private final List<PlayerBowlingBoard> playerBowlingBoardList;

    public PlayerBowlingBoards(List<PlayerBowlingBoard> playerBowlingBoardList) {
        this.playerBowlingBoardList = playerBowlingBoardList;
    }

    public static PlayerBowlingBoards of(List<String> playerName) {
        return IntStream.range(0, playerName.size())
                .mapToObj(index -> PlayerBowlingBoard.of(playerName.get(index), index))
                .collect(collectingAndThen(toList(), PlayerBowlingBoards::new));
    }

    public List<Player> playerList() {
        return playerBowlingBoardList.stream()
                .map(PlayerBowlingBoard::player)
                .collect(Collectors.toList());
    }

    public List<PlayerDto> playerDtoList() {
        return playerList().stream()
                .map(PlayerDto::of)
                .collect(Collectors.toList());
    }

    public List<BowlingBoard> bowlingBoardList() {
        return playerBowlingBoardList.stream()
                .map(PlayerBowlingBoard::bowlingBoard)
                .collect(Collectors.toList());
    }

    public List<BowlingBoardDto> bowlingBoardDtoList() {
        return bowlingBoardList().stream()
                .map(BowlingBoardDto::of)
                .collect(Collectors.toList());
    }

    public boolean isEnd() {
        return false;
    }

    public Stream<PlayerBowlingBoard> stream() {
        return playerBowlingBoardList.stream();
    }

    public Round round(Round round) {
        if (isNextRound(round)) {
            return round.next();
        }
        return round;
    }

    public boolean isNextRound(Round round) {
        return playerBowlingBoardList.stream()
                .noneMatch(playerBowlingBoard -> playerBowlingBoard.round().equals(round));
    }


}
