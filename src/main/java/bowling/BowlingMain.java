package bowling;

import bowling.domain.Bowling;
import bowling.domain.Players;

import static bowling.view.BowlingInputView.countOfPins;
import static bowling.view.BowlingInputView.playerName;
import static bowling.view.BowlingResultView.*;

public class BowlingMain {
    public static void main(String[] args) {

        Players players = new Players
                .Builder(() -> 1)
                .withPlayerNames(playerName())
                .build();

        printEmptyScoreBoard(players);

        Bowling bowling = new Bowling
                .Builder(players, countOfPins())
                .rollEnded(printFrameHeader())
                .peekRecord(printPlayerScore())
                .build();

        bowling.start();

    }
}
