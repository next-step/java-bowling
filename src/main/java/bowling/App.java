package bowling;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.pins.Pins;
import bowling.domain.player.Player;
import bowling.view.InputView;
import bowling.view.ResultView;

public class App {

    public static void main(String[] args) {

        String name = InputView.getInputPlayerName();
        Player player = Player.of(name);
        Frames frames = Frames.of();
        ResultView.printBoard(player, frames);
        while (!frames.isFinish()) {
            Frame frame = frames.getLastFrame();
            int pins = InputView.getInputPitch(frame.getFrameNumber());
            frames.bowl(Pins.of(pins));
            ResultView.printBoard(player, frames);
        }
    }
}
