package bowling;

import bowling.domain.Ball;
import bowling.domain.Bowling;
import bowling.domain.Bowlings;
import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.List;

public class BowlingGame {
    public static void play() {
        List<Player> players = InputView.players();
        Bowlings bowlings = Bowlings.create(players);

        ResultView.print(bowlings.getValues());

        while (bowlings.hasNextPitching()) {
            bowlings.increaseIndexIfFrameEnd();
            bowlings.getValues()
                    .forEach(bowling -> play(bowlings, bowling));
        }
    }

    private static void play(Bowlings bowlings, Bowling bowling) {
        while (bowling.isRunning(bowlings.getCurrentIndex())) {
            String playerName = bowling.getPlayerName();
            Ball ball = InputView.fallenPins(playerName);

            bowling.bowl(ball);

            ResultView.print(bowlings.getValues());
        }
    }
}
