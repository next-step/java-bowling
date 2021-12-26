package bowling;

import bowling.domain.Bowling;
import bowling.domain.Pin;
import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGame {
    public static void play() {
        Player player = InputView.player();
        Bowling bowling = Bowling.create(player);

        ResultView.print(bowling);

        while (bowling.hasNext()) {
            int currentFrameIndex = bowling.getCurrentFrameIndex();
            Pin fallenPins = InputView.fallenPins(currentFrameIndex);

            bowling.bowl(fallenPins);

            ResultView.print(bowling);
        }
    }
}
