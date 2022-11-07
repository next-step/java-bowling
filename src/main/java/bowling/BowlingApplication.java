package bowling;

import java.util.ArrayList;
import java.util.List;

public class BowlingApplication {

    public static void main(String[] args) {

        String userName = InputView.inputUserName();

        List<NormalFrame> frames = new ArrayList<>();

        List<Pins> pins = new ArrayList<>();
        FinalFrame finalFrame = new FinalFrame(pins);

        for (int i = 1; i < 10; i++) {
            int score = InputView.inputUserScore(i);
            NormalFrame frame = NormalFrame.of(i, score);

            frames.add(frame);
            ResultView.printResult(userName, frames, finalFrame);
            if (frame.isFinished()) {
                continue;
            }
            score = InputView.inputUserScore(i);
            frame.bowl(score);
            ResultView.printResult(userName, frames, finalFrame);
        }

        for (int i = 0; i < 3; i++) {
            int score = InputView.inputUserScore(10);
            pins.add(new Pins(score));
            ResultView.printResult(userName, frames, finalFrame);
            if (finalFrame.isFinished()) {
                continue;
            }
            score = InputView.inputUserScore(10);
            pins.add(new Pins(score));
            ResultView.printResult(userName, frames, finalFrame);
        }
    }
}
