package bowling.controller;

import bowling.domain.game.BowlingGames;
import bowling.domain.player.Players;
import bowling.domain.point.Point;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGameRun {

    public static void main(String[] args) {
        Players players = InputView.inputPlayers();
        BowlingGames bowlingGames = new BowlingGames(players);

        while (!bowlingGames.isEnd()) {
            Point point = InputView.inputThrowCount(bowlingGames.nextGame());
            bowlingGames.throwBall(point);
            ResultView.viewResult(bowlingGames);
        }
    }
}
