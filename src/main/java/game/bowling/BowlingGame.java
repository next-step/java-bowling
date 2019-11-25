package game.bowling;

import game.bowling.domain.Bowling;
import game.bowling.view.InputView;
import game.bowling.view.ResultView;

/**
 * Created by yusik on 2019/11/20.
 */
public class BowlingGame {

    public void start() {

        InputView inputView = new InputView(System.in);
        ResultView resultView = new ResultView(System.out);

        String playerName = inputView.receivePlayerName();
        Bowling bowling = new Bowling(playerName);
        resultView.render(bowling.getScoreBoard());

        while(bowling.isNotFinish()) {
            int score = inputView.receiveScore(bowling.nextFrameNo());
            bowling.bowl(score);
            resultView.render(bowling.getScoreBoard());
        }

    }

}
