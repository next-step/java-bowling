package seul.bowling;

import seul.bowling.domain.Frames;
import seul.bowling.domain.Player;
import seul.bowling.view.InputView;
import seul.bowling.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        String playerName = inputView.inputPlayerName();

        Player player = new Player(playerName);

        OutputView.printScoreBoard(playerName, player.getFrames());

        do {
            Frames frames = player.getFrames();
            int clearPinCount = inputView.inputClearPin(frames);

            player.play(clearPinCount);

            OutputView.printScoreBoard(player.getName(), frames);
        } while (!player.isEnd());
    }
}
