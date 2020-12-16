package bowling.controller;

import bowling.domain.Frames;
import bowling.domain.Player;
import bowling.domain.Type;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingController {

    public static void runBowlingGame() {
        String playerName = InputView.inputPlayerName();

        Player player = Player.of(playerName);

        Frames frames = Frames.of();
        Frame frame;
        for (int i = 1; i <= 10; i++) {
            frame = Frame.of();
            Type firstType = frame.first(InputView.inputPitchBowl(i));
            if (firstType == Type.STRIKE) {
                continue;
            }
            frame.second(InputView.inputPitchBowl(i));
            frames.inputFrame(frame);
            ResultView.printBowlResult(frames);
        }
    }
}
