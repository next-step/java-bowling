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
            printBowlingGamesResult(bowlingGames.results());
            Pinfall pinfall = Pinfall.create(View.pinfall(bowlingGames.currentPlayer().name()));
            bowlingGames.roll(pinfall);
        }
        printBowlingGamesResult(bowlingGames.results());
    }

    private void printBowlingGamesResult(List<BowlingResult> bowlingResults) {
        View.printScoreBoardHeader();
        bowlingResults.stream()
                .map(SimpleViewFrameResult::new)
                .forEach(View::printBowlingResult);
    }
}
