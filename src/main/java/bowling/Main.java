package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.Pin;
import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.OutputView;

public class Main {
    public static void main(String[] args) {
        String playerName = InputView.printPlayerName();
        Player player = new Player(playerName);

        OutputView.printDefault(player.getName());

        BowlingGame bowlingGame = new BowlingGame(player);

        while (!bowlingGame.isEndGame()) {
            int pin = InputView.printFramePitches(bowlingGame.getCurrentFrameNumber());
            bowlingGame.play(new Pin(pin));
            OutputView.printResult(bowlingGame);
        }
    }
}
