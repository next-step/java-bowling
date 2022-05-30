package bowling;

import bowling.domain.Boards;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.List;

public class BowlingApplication {

    private static final String RESTART_GAME_MESSAGE = "[INFO] 게임을 재시작 합니다.\n";

    public static void main(String[] args) {
        playGame();
    }

    private static void playGame() {
        int playerCount = InputView.inputPlayerCount();
        List<String> playerNames = InputView.inputPlayerNames(playerCount);
        Boards boards = new Boards(playerNames);

        while (!boards.isGameEnd()) {
            try {
                String name = boards.name();
                int pins = InputView.inputPins(name);
                boards.bowl(pins);
                ResultView.printResults(boards.boards());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println(RESTART_GAME_MESSAGE);
                playGame();
            }
        }
        ResultView.printGameEndMessage();
    }
}
