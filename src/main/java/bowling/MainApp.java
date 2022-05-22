package bowling;

import bowling.controller.BowlingGameController;
import bowling.domain.Game;
import bowling.ui.InputCui;
import bowling.ui.OutputCui;

public class MainApp {

    public static void main(String[] args) {
        InputCui inputCui = new InputCui();
        OutputCui outputCui = new OutputCui();
        BowlingGameController bowlingGame = new BowlingGameController();

        final String name = inputCui.inputPlayerNames();
        Game game = bowlingGame.createFrames(name);
        outputCui.drawStatus(game);

        play(inputCui, outputCui, game);
    }

    private static void play(InputCui inputCui, OutputCui outputCui, Game game) {
        int hitCount;
        while (!game.isEndGame()) {
            hitCount = inputCui.inputHitCount(game.frameNumber());

            game.throwBall(hitCount);

            outputCui.drawStatus(game);
        }
    }

}
