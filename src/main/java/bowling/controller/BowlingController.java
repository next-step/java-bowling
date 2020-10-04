package bowling.controller;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.game.BowlingGame;
import bowling.domain.player.Player;
import bowling.view.ResultView;

import static bowling.view.InputView.inputPlayer;
import static bowling.view.InputView.inputScore;

public class BowlingController {

    public static void start() {
        Player player = inputPlayer();
        BowlingGame bowlingGame = BowlingGame.of(player);

        while (!bowlingGame.isGameOver()) {
            bowlingGame.nextFrame();
            playGame(player, bowlingGame.getFrames());
        }
    }

    private static void playGame(Player player, Frames frames) {
        Frame frame = frames.findLastFrame();
        while (frame.isPlayable()) {
            frame.addScore(inputScore(frames.size() - 1));
            ResultView.printBowlingScore(player, frames);
        }
    }
}
