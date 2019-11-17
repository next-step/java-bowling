import view.InputView;
import view.ResultView;

import java.util.ArrayList;
import java.util.List;

public class BowlingApplication {
    public static void main(String[] args) {
        String name = InputView.inputPlayer();
        ResultView.printInitialScoreBoard(name);
        List<List<Integer>> scores = new ArrayList<>();
        for (int frame = 1; frame <= 10; frame++) {
            List<Integer> score = new ArrayList<>();
            int firstScore = InputView.inputFrame(frame);
            score.add(firstScore);
            scores.add(score);
            ResultView.printScoreBoard(name, scores);
            int secondScore = 0;

            if (firstScore < 10) {
                secondScore = InputView.inputFrame(frame);
                score.add(secondScore);
                ResultView.printScoreBoard(name, scores);
            }
            if (frame == 10 && firstScore + secondScore == 10) {
                int anotherScore = InputView.inputFrame(frame);
                score.add(anotherScore);
                ResultView.printScoreBoard(name, scores);
            }
        }
    }
}
