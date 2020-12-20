package bowling.controller;

import bowling.domain.BowlingGame;
import bowling.domain.DownedPin;
import bowling.domain.User;
import bowling.dto.UserDTO;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingController {
    public BowlingController() {
    }

    public void run() {
        User user = new User(InputView.askName());
        UserDTO userDTO = user.exportData();
        BowlingGame game = new BowlingGame();

        while (!game.isEnd()) {
            ResultView.printCurrentRound(game.exportData());
            game.record(DownedPin.fromNumber(InputView.askScore()));

            ResultView.printRoundDescription();
            ResultView.printGameStatus(userDTO, game.exportData());
        }
    }
}
