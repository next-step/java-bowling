package bowling;

import bowling.domain.frame.Frames2;
import bowling.domain.player.Player;
import bowling.domain.point.Point;
import bowling.domain.view.InputView;
import bowling.domain.view.ResultView;

public class Main {
    public static void main(String[] args) {
        Player player = new Player(InputView.inputPlayerName());
        ResultView.getDefaultBoard(player);
        Frames2 frames = Frames2.generate();

        while(!frames.isGameOver()) {
            Point point = InputView.inputRoll(frames.getFrameIndex());
            frames = frames.roll(point);

            ResultView.printResult(player, frames);
            frames.nextFrame();
        }


    }
}
