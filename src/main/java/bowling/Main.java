package bowling;

import bowling.domain.PlayerGame;
import bowling.domain.frame.Frame;
import bowling.view.InputView;
import bowling.view.ResultView;

public class Main {
    public static void main(String[] args) {
        PlayerGame playerGame = new PlayerGame(InputView.receivePlayerName());
        Frame frame = playerGame.getCurrentFrame();

        ResultView.printResult(playerGame);
        while (!playerGame.isGameEnd()) {
            frame = playerGame.play(InputView.receivePinCount(frame.getFrameNumber()));
            ResultView.printResult(playerGame);
        }
    }
}
