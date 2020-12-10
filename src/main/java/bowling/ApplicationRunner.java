package bowling;

public class ApplicationRunner {
    public static void main(String[] args) {
        BowlingGame bowlingGame = getBowlingGameRunner();
        bowlingGame.run();
    }

    private static BowlingGame getBowlingGameRunner() {
        return new BowlingGame(getConsoleInputView(), getConsoleResultView());
    }

    private static ConsoleInputView getConsoleInputView() {
        return new ConsoleInputView();
    }

    private static ConsoleResultView getConsoleResultView() {
        return new ConsoleResultView();
    }
}
