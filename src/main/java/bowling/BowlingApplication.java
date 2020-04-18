package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingApplication {

    public static void main(String[] args) {

        String name = InputView.inputPlayerName().trim();
        Player player = new Player(name);

        int frameNumber = 1;
        BowlingGame bowlingGame = new BowlingGame(player);

        while (!bowlingGame.isFinish()) {
            int inputBowlCount =
                    Integer.parseInt(InputView.inputBowlCount(frameNumber));
            bowlingGame.play(inputBowlCount);

            ResultView.printScoreBoardTop();
            ResultView.printScoreBoardPlayer(bowlingGame);

            if (bowlingGame.isLastFrameFinish()) {
                frameNumber++;
            }
        }
    }
}
