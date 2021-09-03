package bowling;

import bowling.domain.Frame;
import bowling.domain.Frames;
import bowling.domain.NormalFrame;
import bowling.domain.Pins;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingApplication {
    public static void main(String args[]) {
        String player = InputView.getPlayerName();

        Frame frame = new NormalFrame(1);
        while (!frame.isFinish()) {
            frame = frame.bowl(Pins.of(InputView.getFrameScore(frame.getFrameNumber())));
        }

    }
}
