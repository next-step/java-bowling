package bowling;

import bowling.game.domain.GamePlay;
import bowling.game.domain.GamePlayAssembler;
import bowling.pin.domain.Pin;
import bowling.player.domain.PlayerAssembler;
import bowling.view.InputView;
import bowling.view.OutputView;

import java.util.List;

public class MainApplication {

    public void run() {
        List<String> playersName = InputView.InputPlayerNames();
        GamePlay gamePlay = GamePlay.play(playersName);
        OutputView.print(GamePlayAssembler.assemble(gamePlay));
        while (!gamePlay.isGameOver()) {
            OutputView.printCurrentUser(PlayerAssembler.assemble(gamePlay.getCurrentPlayer()));
            gamePlay.roll(Pin.of(InputView.getFelled()));
            OutputView.print(GamePlayAssembler.assemble(gamePlay));
        }
    }

    public static void main(String[] args) {
        new MainApplication().run();
    }

}
