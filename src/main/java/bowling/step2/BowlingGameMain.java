package bowling.step2;

import bowling.step2.domain.BowlingGame;
import bowling.step2.view.InputView;
import bowling.step2.view.ResultView;

public class BowlingGameMain {

    public static void main(String[] args) {
        String name = InputView.inputPlayerName();
        BowlingGame bowlingGame = new BowlingGame(name);
        ResultView.printInitScoreBoard(bowlingGame);

        for (int i = BowlingGame.GAME_START_INDEX; i <= BowlingGame.GAME_LAST_INDEX; i++) {
            playFrame(bowlingGame, i);
        }
    }

    private static void playFrame(BowlingGame bowlingGame, int i) {
        while (!bowlingGame.IsEndOfOneFrame(i)) {
            int score = InputView.inputScore(i);
            bowlingGame.recode(score);
            ResultView.printGameScoreBoard(bowlingGame);
        }
    }
}
