package bowling;

import bowling.game.domain.BowlingGame;
import bowling.game.domain.BowlingGameAssembler;
import bowling.pin.domain.Pin;
import bowling.user.domain.UserAssembler;
import bowling.view.InputView;
import bowling.view.OutputView;
import java.util.List;

public class BowlingMain {

    public static void main(String[] args) {

        List<String> users = InputView.getUserNames();
        BowlingGame game = BowlingGame.of(users);
        OutputView.print(BowlingGameAssembler.assemble(game));
        while (!game.isGameOver()) {
            OutputView.printCurrentUser(UserAssembler.assemble(game.getCurrentUser()));
            game.roll(Pin.of(InputView.getFelled()));
            OutputView.print(BowlingGameAssembler.assemble(game));
        }
    }

}
