package bowling;

import bowling.domain.*;
import bowling.view.View;

import java.util.List;

public class BowlingControl {
    public void play() {
        int numberOfPlayer = View.numberOfPlayers();
        Players players = new Players();

        for (int i = 0; i < numberOfPlayer; i++) {
            players.add(new Player(View.playerName()));
        }

        BowlingGames bowlingGames = new BowlingGames(players);
        while (!bowlingGames.isDone()) {
            List<BowlingResult> bowlingResults = bowlingGames.results();
            View.printScoreBoardHeader();
            for (BowlingResult bowlingResult : bowlingResults) {
                View.printPlayer(bowlingResult.player());
                View.printBowlingResult(bowlingResult);
            }
            Pinfall pinfall = new Pinfall(View.pinfall(bowlingGames.currentFrameNumber().number()));
            bowlingGames.roll(pinfall);
        }
        List<BowlingResult> bowlingResults = bowlingGames.results();
        View.printScoreBoardHeader();
        for (BowlingResult bowlingResult : bowlingResults) {
            View.printPlayer(bowlingResult.player());
            View.printBowlingResult(bowlingResult);
        }
    }
}
