package bowling;

import bowling.domain.game.Bowling;
import bowling.domain.player.Player;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGame {
    public static void main(String[] args) {
        Player player = Player.of(InputView.doInputPlayer());

        Bowling bowling = new Bowling(player);

        while ((!bowling.isEnd())) {
            int countOfPins = InputView.doInputCountOfPins(bowling.getFrameNumber());
            bowling.bowl(countOfPins);

            ResultView.printBoard(bowling);
        }
    }
}
