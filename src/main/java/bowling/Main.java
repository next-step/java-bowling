package bowling;

import bowling.domain.Player;
import bowling.domain.frame.Frames;
import bowling.domain.pin.Pin;
import bowling.ui.InputView;
import bowling.ui.OutputView;

public class Main {

    public static void main(String[] args) {
        Player player = InputView.promptPlayer();
        Frames frames = new Frames();

        while (!frames.finished()) {
            int pinNo = InputView.promptPinNo(frames.currentFrame());
            frames.bowl(Pin.of(pinNo));
            OutputView.printBowling(player, frames);
        }
    }

}
