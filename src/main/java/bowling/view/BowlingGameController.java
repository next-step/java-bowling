package bowling.view;

import bowling.application.BowlingGame;
import bowling.domain.Board;
import bowling.domain.Boards;
import bowling.domain.player.Players;
import bowling.domain.score.Score;

public class BowlingGameController {

    private final BowlingGame bowlingGame;

    public BowlingGameController(BowlingGame bowlingGame) {
        this.bowlingGame = bowlingGame;
    }

    public void playGame() {
        int playerCount = InputView.askPlayerCount();
        Players players = InputView.askPlayers(playerCount);

        Boards boards = bowlingGame.startGame(players);

        while (!boards.isAllBoardFinished()) {
            Board currentBoard = boards.getCurrentBoard();
            Score score = InputView.askScore(currentBoard.getPlayer());
            bowlingGame.addScore(boards, score);
            OutputView.printBoards(boards);
        }
    }
}
