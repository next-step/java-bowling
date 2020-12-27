package bowling.controller;

import bowling.domain.frame.DownedPin;
import bowling.domain.game.BowlingGame;
import bowling.domain.user.User;
import bowling.dto.UserDTO;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingController {

    public void run() {
        User user = new User(InputView.askName());
        UserDTO userDTO = user.exportData();
        BowlingGame bowlingGame = new BowlingGame();

        ResultView.printRoundDescription();
        ResultView.printContent(userDTO, bowlingGame.createInformation().createBowlingGameInformationDTO());

        while (!bowlingGame.isEnd()) {
            ResultView.printCurrentStage(bowlingGame.createInformation().createBowlingGameInformationDTO());
            bowlingGame.record(DownedPin.fromNumber(InputView.askScore()));

            ResultView.printRoundDescription();
            ResultView.printContent(userDTO, bowlingGame.createInformation().createBowlingGameInformationDTO());
        }
    }
}
