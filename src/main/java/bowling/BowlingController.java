package bowling;

import bowling.domain.bowling.Bowling;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.io.PrintWriter;
import java.util.Scanner;

public class BowlingController {
    public static void main(String[] args) {
        PrintWriter output = new PrintWriter(System.out, true);
        InputView inputView = new InputView(new Scanner(System.in), output);
        ResultView resultView = new ResultView(output);

        Bowling bowling = Bowling.of(inputView.enterMemberName());
        showFrames(resultView, bowling);
        startBowling(inputView, resultView, bowling);

        output.close();
    }

    private static void startBowling(InputView inputView, ResultView resultView, Bowling bowling) {
        while (!bowling.isFinished()) {
            bowling.throwBall(inputView.enterScore(bowling.getCurrentFrameNumber()));
            resultView.showFrames(bowling.getName(), bowling.getFrames());
        }
    }

    private static void showFrames(ResultView resultView, Bowling bowling) {
        resultView.showFrames(bowling.getName(), bowling.getFrames());
    }
}
