package bowling;

import bowling.domain.Board;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingApplication {

    private static final String RESTART_GAME_MESSAGE = "[INFO] 게임을 재시작 합니다.\n";

    public static void main(String[] args) {
        playGame();
    }

    private static void playGame() {
        String playerName = InputView.inputPlayerName();
        Board board = new Board(playerName);
        int frameNumber = 1;

        while (!board.isGameEnd()) {
            try {
                int pins = InputView.inputPins(frameNumber);
                frameNumber = board.bowl(pins);
                ResultView.printResult(board);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println(RESTART_GAME_MESSAGE);
                playGame();
            }
        }
        ResultView.printGameEndMessage();
    }
}
