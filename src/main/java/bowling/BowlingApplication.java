package bowling;

import bowling.domain.Bowling;
import bowling.domain.Player;
import bowling.domain.frame.Frames;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingApplication {

    public static void main(String[] args) {
        Bowling bowling = new Bowling();
        InputView inputView = new InputView();

        Player player = inputView.displayPlayerNameInputMessage();
        ResultView resultView = new ResultView(player, bowling);

        for (int i = 0; i <= Frames.BOWLING_GAME_FRAME;) {
            i += bowling.addPlayerScore(i, inputView.displayScoreInputMessage(i));
            resultView.displayResult();
        }

    }
}
