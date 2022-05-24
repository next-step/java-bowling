package bowling;

import static bowling.ui.InputCui.inputHitCount;
import static bowling.ui.InputCui.inputParticipants;
import static bowling.ui.OutputCui.drawStatus;

import bowling.controller.BowlingGameController;
import bowling.domain.Game;

public class MainApp {

    public static void main(String[] args) {
        BowlingGameController bowlingGame = new BowlingGameController();

        final String name = inputParticipants();
        Game game = bowlingGame.createFrames(name);
        drawStatus(game);

        play(game);
    }

    private static void play(Game game) {
        while (!game.isEndGame()) {
            int hitCount = inputHitCount(game.frameNumber());

            game.throwBall(hitCount);

            drawStatus(game);
        }
    }

}
