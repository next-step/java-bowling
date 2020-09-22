package bowling.controller;

import bowling.domain.frame.Frames;
import bowling.domain.frame.ScoreBoard;
import bowling.domain.frame.dto.ScoreBoardAssembler;
import bowling.domain.pin.Pin;
import bowling.domain.user.User;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingController {

    public void start() {
        User user = User.of(InputView.getUserName());
        Frames frames = Frames.init();
        ScoreBoard scoreBoard = ScoreBoard.init(user, frames);

        OutputView.print(ScoreBoardAssembler.assemble(scoreBoard, frames));

        while (!scoreBoard.isGameOver()) {
            OutputView.printCurrentFrame(frames.getLastIndex());
            scoreBoard.bowl(Pin.of(InputView.getFelledPin()));
            OutputView.print(ScoreBoardAssembler.assemble(scoreBoard, frames));
        }
    }
}
