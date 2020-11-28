package bowling;

import bowling.domain.bowling.Bowling;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.io.PrintWriter;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class BowlingController {
    static PrintWriter output = new PrintWriter(System.out, true);

    public static void main(String[] args) {
        InputView inputView = new InputView(new Scanner(System.in), output);
        ResultView resultView = new ResultView(output);

        Bowling bowling = retryIfErrorThrown(() -> Bowling.of(inputView.enterMemberName()));
        showFrames(resultView, bowling);
        startBowling(inputView, resultView, bowling);

        output.close();
    }

    private static <T> T retryIfErrorThrown(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (Exception ex) {
            showError(ex);
            return retryIfErrorThrown(supplier);
        }
    }

    private static void showFrames(ResultView resultView, Bowling bowling) {
        resultView.showFrames(bowling.getName(), bowling.getFrames());
    }

    private static void startBowling(InputView inputView, ResultView resultView, Bowling bowling) {
        while (!bowling.isFinished()) {
            retryIfErrorThrown(bowling::throwBall, () -> inputView.enterScore(bowling.getCurrentFrameNumber()));
            resultView.showFrames(bowling.getName(), bowling.getFrames());
            resultView.showCalculatedScores(bowling.calculateScores());
        }
    }

    private static <T> void retryIfErrorThrown(Consumer<T> consumer, Supplier<T> supplier) {
        try {
            consumer.accept(supplier.get());
        } catch (Exception ex) {
            showError(ex);
            retryIfErrorThrown(consumer, supplier);
        }
    }

    private static void showError(Exception ex) {
        output.println(ex.getMessage());
        output.println();
    }

}
