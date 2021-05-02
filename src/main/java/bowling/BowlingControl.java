package bowling;

import bowling.domain.Bowling;
import bowling.domain.Pinfall;
import bowling.domain.Player;
import bowling.view.View;

public class BowlingControl {
    public void play() {
        String playerName = View.playerName();
        Player player = new Player(playerName);

        Bowling bowling = new Bowling();
        while (!bowling.isDone()) {
            View.printScoreBoardHeader();
            View.printPlayer(player);
            View.printBowlingResult(bowling.result());
            Pinfall pinfall = new Pinfall(View.pinfall(bowling.frameNumber().number()));
            bowling.roll(pinfall);
        }

        View.printScoreBoardHeader();
        View.printPlayer(player);
        View.printBowlingResult(bowling.result());
    }
}
