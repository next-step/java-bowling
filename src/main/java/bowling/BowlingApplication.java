package bowling;

import bowling.domain.Frames;
import bowling.domain.Player;
import bowling.ui.InputView;
import bowling.ui.ResultView;

public class BowlingApplication {
    public static void main(String[] args) {
        String name = InputView.enterName();
        Player player = Player.of(name);
        Frames frames = Frames.init();

        while (!frames.isLast()) {
            int frameIndex = frames.getFrameIndex();
            int downPin = InputView.enterDownPin(frameIndex);
            frames = frames.bowling(downPin);
            ResultView.printResult(player, frames);
            frames.next();
        }

    }
}
