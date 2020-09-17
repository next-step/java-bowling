package bowling.controller;

import bowling.domain.frame.ScoreBoard;
import bowling.domain.frame.dto.ScoreBoardAssembler;
import bowling.domain.pin.Pin;
import bowling.domain.user.User;
import bowling.domain.user.dto.UserAssembler;
import bowling.domain.user.dto.UserDTO;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingController {

    public void start() {
        ScoreBoard scoreBoard = ScoreBoard.init();
        User user = User.of(InputView.getUserName());
        UserDTO userDTO = UserAssembler.assemble(user);

        OutputView.print(userDTO, ScoreBoardAssembler.assemble(scoreBoard));

        while (!scoreBoard.isGameOver()) {
            OutputView.printCurrentFrame(scoreBoard.size());
            scoreBoard.bowl(Pin.of(InputView.getFelledPin()));

            OutputView.print(userDTO, ScoreBoardAssembler.assemble(scoreBoard));
        }
    }
}
