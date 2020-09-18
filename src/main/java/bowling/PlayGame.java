package bowling;

import bowling.domain.Player;
import bowling.play.BowlingGame;
import bowling.view.InputView;
import bowling.view.ResultView;

public class PlayGame {

    private final InputView inputView = new InputView();
    private final ResultView resultView = new ResultView();

    public static void main(String[] args) {
        new PlayGame().start();
    }

    public void start() {
        Player player = new Player(inputView.inputPlayerName());

        BowlingGame bowlingGame = new BowlingGame(player);

        bowlingGame.round(inputView::inputFrameShoot, resultView::showScoreBoard);

    }
}
