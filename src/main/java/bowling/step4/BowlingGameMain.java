package bowling.step4;

import bowling.step4.domain.frame.Frame;
import bowling.step4.domain.process.BowlingGame;
import bowling.step4.domain.process.BowlingGames;
import bowling.step4.view.InputView;
import bowling.step4.view.OutputView;

public class BowlingGameMain {
    public static void main(String[] args) {

        BowlingGames bowlingGames = BowlingGames.of(InputView.inputPlayers());
        while (!bowlingGames.isGamesOver()) {
            playMultiFrames(bowlingGames);
        }
    }

    private static void playMultiFrames(BowlingGames bowlingGames) {
        for (int i = 0; i < bowlingGames.size(); i++) {
            playUntilOneUserEnd(bowlingGames, i);
        }
    }

    private static void playUntilOneUserEnd(BowlingGames bowlingGames, int index) {
        BowlingGame oneGame = bowlingGames.getOneGame(index);
        Frame lastFrame = oneGame.getLastFrame();

        while (!oneGame.isOneFrameOver(lastFrame)){
            oneGame.play(InputView.inputPitch(oneGame));
            OutputView.viewFrameBoard(bowlingGames);
        }
    }
}
