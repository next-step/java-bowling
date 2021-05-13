package bowling;

import bowling.domain.player.Players;

import static bowling.view.InputView.*;
import static bowling.view.ResultView.printEnd;
import static bowling.view.ResultView.showPlayers;

public class BowlingGame {
    public static void main(String[] args) {
        Players players = Players.of(inputName());
        showPlayers(players);
        while (!players.isFinished()) {
            players.play(inputHitNumber(players.whoseTurn()));
            showPlayers(players);
        }
        printEnd(players.getWinner());
        close();
    }
}
