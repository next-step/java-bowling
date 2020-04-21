package bowling.controller;

import bowling.domain.BowlingGame;
import bowling.domain.player.Player;
import bowling.domain.point.Point;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGameRun {

    public static void main(String[] args) {
        Player player = InputView.inputPlayers();
        BowlingGame bowlingGame = new BowlingGame(player);

        while (!bowlingGame.isEnd()) {
            Point point = InputView.inputThrowCount(bowlingGame.currentFrameNo());
            bowlingGame.throwBall(point);
            ResultView.viewResult(bowlingGame);
        }
    }
}
