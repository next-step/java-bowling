package bowling.controller;

import bowling.domain.BowlingGame;
import bowling.domain.DownedPin;
import bowling.domain.User;
import bowling.dto.UserDTO;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingController {

    public void run() {
        User user = new User(InputView.askName());
        UserDTO userDTO = user.exportData();
        BowlingGame bowlingGame = new BowlingGame();

        while (!bowlingGame.isEnd()) {
            bowlingGame.record(DownedPin.fromNumber(InputView.askScore()));
            ResultView.printRoundDescription();
            ResultView.printContent(userDTO, bowlingGame.createBowlingGameDTO());
        }
    }
}
