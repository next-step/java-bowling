package ui;

import domain.BowlingGame;
import domain.Frame;
import domain.Player;
import view.InputView;
import view.OutputView;

public class ConsoleUI {
    public static void main(String[] args) {
        String name = InputView.inputPlayerName();
        Player player = new Player(name);
        BowlingGame.startGame();
        OutputView.playerOfGameBoard(player, BowlingGame.getFrames());

        while (BowlingGame.hasNext()) {
            BowlingGame.play(InputView.inputThrowBall(BowlingGame.getFrames()));
            OutputView.printFrameBoard(Frame.MAX_FRAME);
            OutputView.playerOfGameBoard(player, BowlingGame.getFrames());
        }

    }
}
