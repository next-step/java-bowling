package bowling_step3.controller;


import bowling_step3.domain.BowlingGame;
import bowling_step3.domain.Player;
import bowling_step3.view.InputUi;
import bowling_step3.view.OutputUi;

public class BowlingGameController {
    public static void run() {
        String playerName = InputUi.inputPlayer();
        Player player = Player.of(playerName);
        BowlingGame game = new BowlingGame(player);

        OutputUi.printInitFrame(player, game);

        while (!game.isFinish()) {
            int countOfKnockDown = InputUi.inputFrame(game.getFrameCount());
            game.pitch(countOfKnockDown);
            OutputUi.printInitBowling(playerName, game);
        }
    }
}
