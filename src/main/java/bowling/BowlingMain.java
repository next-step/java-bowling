package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.Frame;
import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingMain {
    private static final int NUMBERS_OF_NORMAL_FRAMES = 10;
    private static final int INITIAL_INDEX = 0;

    public static void main(String[] args) {
        Player player = new Player(InputView.inputPlayerName());

        BowlingGame bowlingGame = new BowlingGame();
        int frameIndex = INITIAL_INDEX;

        while (frameIndex < NUMBERS_OF_NORMAL_FRAMES) {
            Frame frame = bowlingGame.frame(frameIndex);
            frame.delivery(InputView.inputScore(frameIndex + 1));
            ResultView.printBowlingGame(player.getName(), bowlingGame);

            if (frame.additionallyDeliverable()) {
                continue;
            }

            frameIndex++;
        }
    }
}
