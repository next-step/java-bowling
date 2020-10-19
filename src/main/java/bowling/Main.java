package bowling;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.game.Game;
import bowling.domain.pin.Pin;
import bowling.domain.player.Player;
import bowling.domain.view.InputView;
import bowling.domain.view.OutputView;

public class Main {
    public static void main(String[] args) {
        Player player = new Player(InputView.inputPlayerName());
        Game game = new Game(player);

        Frames frames = game.getFrames();
        Frame frame = game.getFirstFrame();
        int frameIndex = 0;

        while (frame.isGameOver()) {
            frameIndex = frames.getFrameIndex(frame);
            Pin pin = InputView.inputPinRoll(frameIndex, frame);
            game.roll(frame, pin);
            frame = frames.getNextFrame();
            OutputView.getScoreBoard(player, frames);
        }
    }
}
