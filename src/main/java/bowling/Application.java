package bowling;

import bowling.game.domain.BowlingGame;
import bowling.game.domain.BowlingGameAssembler;
import bowling.view.InputView;
import bowling.view.OutputView;

public class Application {

    public static void main(String[] args) {
        String playerName = InputView.askPlayerName();
        BowlingGame game = BowlingGame.init(playerName);
        OutputView.print(BowlingGameAssembler.assemble(game));
        while (!game.isDone()) {
            int felledPins = InputView.askFelledPins(BowlingGameAssembler.assemble(game));
            game.bowl(felledPins);
            OutputView.print(BowlingGameAssembler.assemble(game));
        }
    }
}
