package bowling;

import java.util.ArrayList;
import java.util.List;

public class BowlingApplication {

    public static void main(String[] args) {

        String userName = InputView.inputUserName();

        List<NormalFrame> frames = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            int score = InputView.inputUserScore(i);
            NormalFrame frame = NormalFrame.of(i, score);

            frames.add(frame);
            ResultView.printResult(userName, frames);
            if (frame.isFinished()) {
                continue;
            }
            score = InputView.inputUserScore(i);
            frame.bowl(score);
            ResultView.printResult(userName, frames);
        }
    }
}
