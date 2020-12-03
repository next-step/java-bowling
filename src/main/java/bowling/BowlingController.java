package bowling;

import bowling.domain.bowling.Bowling;
import bowling.domain.bowling.Bowlings;
import bowling.domain.pin.Pin;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.io.PrintWriter;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BowlingController {
    static PrintWriter output = new PrintWriter(System.out, true);

    public static void main(String[] args) {
        InputView inputView = new InputView(new Scanner(System.in), output);
        ResultView resultView = new ResultView(output);
        Bowlings bowlings = IntStream.range(0, retryIfErrorThrown(inputView::enterMemberCount))
                .mapToObj(memberIndex -> retryIfErrorThrown(() -> Bowling.of(inputView.enterMemberName(memberIndex + 1))))
                .collect(Collectors.collectingAndThen(Collectors.toList(), Bowlings::of));
        resultView.showScores(bowlings.getBowlings());
        startBowling(inputView, resultView, bowlings);
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

    private static void startBowling(InputView inputView, ResultView resultView, Bowlings bowlings) {
        while (!bowlings.isFinished()) {
            retryIfErrorThrown(bowlings::throwCurrentMemberBall, () -> Pin.of(inputView.enterScore(bowlings.getCurrentMemberName())));
            resultView.showScores(bowlings.getBowlings());
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
