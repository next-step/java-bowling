package bowling.controller;

import bowling.domain.frame.Frames;
import bowling.domain.player.Player;
import bowling.domain.point.Point;
import bowling.ui.InputView;
import bowling.ui.ResultView;

public class BowlingGameController {

    public static void main(String[] args) {
        Player player = InputView.inputPlayer();
        ResultView.printDefaultScoreBoard(player);
        Frames frames = Frames.create();

        while (!frames.isLast()) {
            Point point = InputView.inputPoint(frames.getFrameIndex());
            frames = frames.pitch(point);

            ResultView.printResult(player, frames);
            frames.next();
        }
    }
}
