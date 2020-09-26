package bowling.controller;

import bowling.domain.game.BowlingGame;
import bowling.domain.game.dto.BowlingGameAssembler;
import bowling.domain.pin.Pin;
import bowling.domain.user.dto.UserAssembler;
import bowling.view.InputView;
import bowling.view.OutputView;

import java.util.List;

public class BowlingController {

    public void start() {
        List<String> users = InputView.getUserNames();
        BowlingGame bowlingGame = BowlingGame.of(users);

        OutputView.print(BowlingGameAssembler.assemble(bowlingGame));

        while (!bowlingGame.isGameOver()) {
            OutputView.printCurrentUser(UserAssembler.assemble(bowlingGame.getCurrentUser()));
            bowlingGame.bowl(Pin.of(InputView.getFelledPin()));
            OutputView.print(BowlingGameAssembler.assemble(bowlingGame));
        }
    }
}
