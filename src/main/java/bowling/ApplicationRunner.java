package bowling;

public class ApplicationRunner {
    public static void main(String[] args) {
        BowlingGameRunner bowlingGameRunner = getBowlingGameRunner();
        bowlingGameRunner.run();
    }

    private static BowlingGameRunner getBowlingGameRunner() {
        return new BowlingGameRunner(getConsoleInputView(), getConsoleResultView());
    }

    private static ConsoleInputView getConsoleInputView() {
        return new ConsoleInputView();
    }

    private static ConsoleResultView getConsoleResultView() {
        return new ConsoleResultView();
    }
}
