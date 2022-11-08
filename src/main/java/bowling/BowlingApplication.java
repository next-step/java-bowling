package bowling;

import java.util.ArrayList;
import java.util.List;

public class BowlingApplication {

    public static void main(String[] args) {

        String userName = InputView.inputUserName();

        List<Frame> frames = new ArrayList<>();

        for (int i = 1; i < 11; i++) {

            int score = InputView.inputUserScore(i);

            if (i != 10) {
                NormalFrame frame = NormalFrame.of(i, score);
                frames.add(frame);
                ResultView.printResult(userName, frames);

                if (frame.isFinished()) {
                    continue;
                }
                score = InputView.inputUserScore(i);
                frame.bowl(score);
                ResultView.printResult(userName, frames);

            } else {
                FinalFrame frame = FinalFrame.of(score);
                frames.add(frame);
                ResultView.printResult(userName, frames);

                while (!frame.isFinished()) {
                    score = InputView.inputUserScore(i);
                    frame.bowl(score);
                    ResultView.printResult(userName, frames);
                }
            }
        }
    }
}
