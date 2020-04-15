package bowling;

import bowling.domain.FinalFrame;
import bowling.domain.Game;
import bowling.domain.NormalFrame;
import bowling.domain.PlayBowling;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingController {
    private static InputView inputView = InputView.getInputView();
    private static ResultView resultView = ResultView.getResultView();
    private static PlayBowling playBowling = PlayBowling.getPlayBowling();

    public static void main(String[] args) {
        String userName = inputView.enterUserName();
        System.out.println(resultView.initScoreBoard(userName));

        Game game = new Game(userName);
        NormalFrame normalFrame = game.startGame();
        playBowling.playFrame(game, normalFrame);

        for (int i = 2; i < 10; i++) {
            normalFrame = normalFrame.createNextFrame();
            playBowling.playFrame(game, normalFrame);
        }

        FinalFrame finalFrame = normalFrame.createFinalFrame(playBowling.isBonusFlag());
        playBowling.playFinalFrame(game, finalFrame);

    }

}
