package bowling;

import bowling.domain.frame.FrameNumber;
import bowling.domain.game.Game;
import bowling.domain.player.Player;
import bowling.domain.state.Pin;
import bowling.view.InputView;
import bowling.view.OutputView;

public class Application {
    public static void main(String[] args) {
        Player player = new Player(InputView.inputPlayerName());
        Game game = Game.enter(player);

        while (!game.isDone()) {
            game.bowl(inputPinCount(game.currentFrameNumber()));
            OutputView.printBoard(game);
        }

        OutputView.printGameIsDone();
    }

    public static Pin inputPinCount(FrameNumber frameNumber) {
        return new Pin(InputView.inputPinCount(frameNumber));
    }
}
