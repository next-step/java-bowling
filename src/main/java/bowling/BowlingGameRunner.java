package bowling;

public class BowlingGameRunner {
    private final ConsoleInputView consoleInputView;
    private final ConsoleResultView consoleResultView;

    public BowlingGameRunner(ConsoleInputView consoleInputView, ConsoleResultView consoleResultView) {
        this.consoleInputView = consoleInputView;
        this.consoleResultView = consoleResultView;
    }

    public void run() {
        String playerName = ValidInputHelper.get(consoleInputView::getPlayerName, consoleInputView::printError);

        BowlingGame bowlingGame = BowlingGame.init(playerName);

        while (!bowlingGame.isEnd()) {
            setKnockDownPins(bowlingGame);
            consoleResultView.print(bowlingGame);
        }
    }

    private void setKnockDownPins(BowlingGame bowlingGame) {
        try {
            int knockDownPins = consoleInputView.getKnockDownPins(bowlingGame.getCurrentFrameIndex());
            bowlingGame.setKnockDownPins(knockDownPins);
        } catch (RuntimeException e) {
            consoleInputView.printError(e);
            setKnockDownPins(bowlingGame);
        }
    }
}
