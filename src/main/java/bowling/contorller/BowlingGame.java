package bowling.contorller;

import bowling.domain.bowlingboard.PlayerBowlingBoards;
import bowling.domain.frame.round.Round;
import bowling.domain.score.Point;
import bowling.dto.PlayerBowlingBoardDto;
import bowling.view.InputView;
import bowling.view.OutputView;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class BowlingGame {

    private final PlayerBowlingBoards playerBowlingBoards;

    private BowlingGame(List<String> playerName) {
        this.playerBowlingBoards = PlayerBowlingBoards.of(playerName);
    }

    public static BowlingGame of(int playerCount) {
        List<String> PlayerNames = IntStream.rangeClosed(1, playerCount)
                .mapToObj(InputView::inputPlayer)
                .collect(toList());
        return new BowlingGame(PlayerNames);
    }

    public void play() {
        OutputView.printResultView(PlayerBowlingBoardDto.of(playerBowlingBoards));
        PlayerBowlingBoards nextBoard = playerBowlingBoards;
        Round currentRound = Round.first();
        while (!nextBoard.isEnd()) {
            Round finalCurrentRound = currentRound;
            nextBoard.stream()
                    .forEach(playerBowlingBoard -> play(nextBoard, finalCurrentRound, playerBowlingBoard));
            currentRound = nextBoard.round(currentRound);
        }
    }

    private void play(PlayerBowlingBoards nextBoard, Round finalCurrentRound, bowling.domain.bowlingboard.PlayerBowlingBoard playerBowlingBoard) {
        if (finalCurrentRound.equals(playerBowlingBoard.round())) {
            int round = finalCurrentRound.round();
            int point = InputView.inputPoint(playerBowlingBoard.player());
            playerBowlingBoard.pitching(Point.of(point), finalCurrentRound);
            OutputView.printResultView(round, PlayerBowlingBoardDto.of(nextBoard, playerBowlingBoard.player()));
        }
    }

    public static void main(String[] args) {
        int playerCount = InputView.playerCount();
        BowlingGame gameController = BowlingGame.of(playerCount);
        gameController.play();
    }
}
