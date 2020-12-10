package bowling;

public class BowlingGameRunner {
    public static void main(String[] args) {
        ConsoleInputView consoleInputView = new ConsoleInputView();
        ConsoleResultView consoleResultView = new ConsoleResultView();
        String playerName = consoleInputView.getPlayerName();

        BowlingGame bowlingGame = BowlingGame.init(playerName);

        while (!bowlingGame.isEnd()) {
            int knockDownPins = consoleInputView.getKnockDownPins(bowlingGame.getCurrentFrameIndex());
            bowlingGame.setKnockDownPins(knockDownPins);
            consoleResultView.print(bowlingGame);
        }
    }
}
