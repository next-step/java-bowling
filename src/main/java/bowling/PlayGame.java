package bowling;

import bowling.domain.Players;
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
        Players players = inputView.inputPlayers();
        BowlingGame bowlingGame = new BowlingGame(players);
        bowlingGame.start(inputView::inputFrameShoot, resultView::showScoreBoard);
    }
}
