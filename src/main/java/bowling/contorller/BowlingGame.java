package bowling.contorller;

import bowling.domain.Player;
import bowling.domain.bowlingboard.PlayerBowlingBoards;
import bowling.domain.bowlingboard.ThrowCount;
import bowling.domain.frame.round.Round;
import bowling.domain.score.Point;
import bowling.dto.PlayerBowlingBoardDto;
import bowling.view.InputView;
import bowling.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BowlingGame {

    private final PlayerBowlingBoards playerBowlingBoards;

    public BowlingGame(List<String> playerName) {
        this.playerBowlingBoards = PlayerBowlingBoards.of(playerName);
    }

    public static BowlingGame of(int playerCount) {
        List<String> PlayerNames = IntStream.rangeClosed(1, playerCount)
                .mapToObj(i -> InputView.inputPlayer(playerCount))
                .collect(Collectors.toList());
        return new BowlingGame(PlayerNames);
    }

    public void play() {
        OutputView.printResultView(PlayerBowlingBoardDto.of(playerBowlingBoards.playerList()));
        PlayerBowlingBoards nextBoard = playerBowlingBoards;
        Player currentThrowPlayer = nextBoard.firstThrowPlayer();
        Round currentRound = Round.first();
        while (!nextBoard.isEnd()) {
            int point = InputView.inputPoint(currentThrowPlayer);
            nextBoard = nextBoard.pitching(currentRound, Point.of(point), currentThrowPlayer);
            currentThrowPlayer = nextBoard.nextPlayer(currentThrowPlayer);
            currentRound = nextBoard.round(currentRound);
            int round = round(nextBoard, currentRound);
            OutputView.printResultView(round, PlayerBowlingBoardDto.of(nextBoard.playerList(), nextBoard.bowlingBoardList()));
        }
    }

    private int round(PlayerBowlingBoards nextBoard, Round currentRound) {
        if (nextBoard.state().equals(ThrowCount.fisrt()) && Round.first() != currentRound) {
            return currentRound.round() - 1;
        }
        return currentRound.round();
    }

    public static void main(String[] args) {
        int playerCount = InputView.playerCount();
        BowlingGame gameController = BowlingGame.of(playerCount);
        gameController.play();
    }
}
