import domain.BowlingGame;
import domain.NormalFrame;
import domain.Pins;
import domain.Player;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        Player player = Player.from(InputView.askPlayerName());

        BowlingGame bowlingGame = BowlingGame.from(player);
        OutputView.printBoard(bowlingGame);

        int fallenPins = InputView.askFallenPins(1);
    }
}
