package com.seok2.bowling;

import com.seok2.bowling.game.domain.BowlingGame;
import com.seok2.bowling.game.domain.BowlingGameAssembler;
import com.seok2.bowling.pin.domain.Pin;
import com.seok2.bowling.user.domain.UserAssembler;
import com.seok2.bowling.view.InputView;
import com.seok2.bowling.view.OutputView;
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
