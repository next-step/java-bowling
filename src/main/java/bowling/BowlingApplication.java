package bowling;

import bowling.domain.Player;
import bowling.domain.frame.Frames;
import bowling.domain.frame.LastFrame;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingApplication {
    private static final int ONE = 1;

    public static void main(String[] args) {
        Player player = new Player(InputView.inputPlayer());
        ResultView.printBowlingFrame(player);

        Frames frames = BowlingGame.startGame(player);
        while (!frames.isOver()) {
            BowlingGame.nexrFrame(frames);
            playBowling(player, frames);
        }
    }

    private static void playBowling(Player player, Frames frames) {
        while (frames.isPlayableFrame()) {
            BowlingGame.play(frames.getLast(), InputView.relaseBowling(frames.size() - ONE));
            ResultView.printBowlingScore(player, frames);
        }
    }
}
