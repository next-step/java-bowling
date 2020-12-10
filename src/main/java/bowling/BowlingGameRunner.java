package bowling;

public class BowlingGameRunner {
    public static void main(String[] args) {
        ConsoleInputView consoleInputView = new ConsoleInputView();
        ConsoleResultView consoleResultView = new ConsoleResultView();
        String playerName = ValidInputHelper.get(consoleInputView::getPlayerName, consoleInputView::printError);

        BowlingGame bowlingGame = BowlingGame.init(playerName);

        while (!bowlingGame.isEnd()) {
            setKnockDownPins(bowlingGame, consoleInputView);
            consoleResultView.print(bowlingGame);
        }
    }

    private static void setKnockDownPins(BowlingGame bowlingGame, ConsoleInputView consoleInputView) {
        try {
            int knockDownPins = consoleInputView.getKnockDownPins(bowlingGame.getCurrentFrameIndex());
            bowlingGame.setKnockDownPins(knockDownPins);
        } catch (RuntimeException e) {
            consoleInputView.printError(e);
            setKnockDownPins(bowlingGame, consoleInputView);
        }
    }
}
