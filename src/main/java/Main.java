import bowling.Pin;
import bowling.Player;
import bowling.dto.BowlingGameBoardResult;
import bowling.game.BowlingGame;
import bowling.game.BowlingGameBoard;
import bowling.view.InputView;
import bowling.view.OutputView;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        int playerCount = InputView.inputPlayerCount();

        List<Player> players = InputView.inputPlayerNames(playerCount);

        BowlingGameBoard bowlingGameBoard = BowlingGameBoard.newInstance(players);

        OutputView.printBowlingGameBoard(BowlingGameBoardResult.newInstance(bowlingGameBoard));

        while (!bowlingGameBoard.isAllGameOver()) {
            BowlingGame bowlingGame = bowlingGameBoard.getNextTurn();
            Player player = bowlingGame.getPlayer();

            int pinCount = InputView.inputDropPinCount(player);
            bowlingGame.bowl(Pin.of(pinCount));

            OutputView.printBowlingGameBoard(BowlingGameBoardResult.newInstance(bowlingGameBoard));
        }
    }
}
