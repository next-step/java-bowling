package bowling;

import java.util.ArrayList;
import java.util.List;

public class BowlingApplication {

    public static void main(String[] args) {

        UserName userName = InputView.inputUserName();

        Frames frames = new Frames();
        ResultView.printResult(userName, frames);

        for (int i = NormalFrame.MIN_FRAMES; i <= FinalFrame.FINAL_FRAME_NUM ; i++) {

            int score = InputView.inputUserScore(i);

            if (i != FinalFrame.FINAL_FRAME_NUM) {
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

                if (frame.isFinished()) {
                    continue;
                }
                score = InputView.inputUserScore(i);
                frame.bowl(score);
                ResultView.printResult(userName, frames);

                if (!frame.isFinished()) {
                    score = InputView.inputUserScore(i);
                    frame.bonusBowl(score);
                    ResultView.printResult(userName, frames);
                }
            }
        }
    }
}
