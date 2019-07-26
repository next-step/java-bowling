import domain.BowlingGame;
import domain.BowlingMatch;
import domain.Pins;
import domain.Player;
import domain.frame.FrameIndex;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        int numberOfPlayers = InputView.askNumberOfPlayers();
        List<Player> players = new ArrayList<>();

        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(Player.from(InputView.askPlayerName()));
        }

        BowlingGame bowlingGame = BowlingGame.from(players);
        OutputView.printBoard(bowlingGame);

        while(!bowlingGame.isGameOver()) {
            FrameIndex currentFrameIndex = bowlingGame.currentFrameIndex();
            Pins fallenPins = InputView.askFallenPins(currentFrameIndex);

            bowlingGame.play(fallenPins);
            OutputView.printBoard(bowlingGame);
        }
    }
}
