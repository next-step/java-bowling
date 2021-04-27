package bowling.domain.bowlingboard;

import bowling.domain.Player;
import bowling.domain.ThrowsState;
import bowling.domain.frame.round.Round;
import bowling.domain.score.Point;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class PlayerBowlingBoards {

    private final List<PlayerBowlingBoard> playerBowlingBoardList;
    private final ThrowsState throwsState;

    public PlayerBowlingBoards(List<PlayerBowlingBoard> playerBowlingBoardList, ThrowsState throwsState) {
        this.playerBowlingBoardList = playerBowlingBoardList;
        this.throwsState = throwsState;
    }

    public static PlayerBowlingBoards of(List<String> playerName) {
        return IntStream.range(0, playerName.size())
                .mapToObj(index -> PlayerBowlingBoard.of(playerName.get(index), index))
                .collect(collectingAndThen(toList(), playerBowlingBoards -> new PlayerBowlingBoards(playerBowlingBoards, ThrowsState.FIRST_THROWS)));
    }

    public List<Player> playerList() {
        return playerBowlingBoardList.stream()
                .map(PlayerBowlingBoard::player)
                .collect(Collectors.toList());
    }

    public List<BowlingBoard> bowlingBoardList() {
        return playerBowlingBoardList.stream()
                .map(PlayerBowlingBoard::bowlingBoard)
                .collect(Collectors.toList());
    }

    public boolean isEnd() {
        return false;
    }

    public PlayerBowlingBoards pitching(Round round, Point point, Player currentThrowPlayer) {
        playerBowlingBoardList.stream()
                .filter(playerBowlingBoard -> playerBowlingBoard.player().equals(currentThrowPlayer) && playerBowlingBoard.round().equals(round))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 플레이어입니다."))
                .pitching(point);

        return new PlayerBowlingBoards(playerBowlingBoardList, throwsState.next(round));
    }

    public ThrowsState state() {
        return throwsState;
    }

    public Player firstThrowPlayer() {
        return playerBowlingBoardList.get(0).player();
    }

    public Player nextPlayer(Player currentThrowPlayer) {
        return playerBowlingBoardList.stream()
                .filter(playerBowlingBoard -> playerBowlingBoard.isSamePosition(playerPosition(currentThrowPlayer)))
                .findAny()
                .map(PlayerBowlingBoard::player)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 플레이어 입니다."));
    }

    private int playerPosition(Player currentThrowPlayer) {
        return playerBowlingBoardList.stream()
                .filter(playerBowlingBoard -> playerBowlingBoard.isSamePlayer(currentThrowPlayer))
                .findAny()
                .map(PlayerBowlingBoard::player)
                .map(Player::nextPosition)
                .filter(position -> position < playerBowlingBoardList.size() - 1)
                .orElse(0);
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
