package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.Pins;
import bowling.domain.User;
import bowling.domain.dto.ResultDTO;
import bowling.view.InputView;
import bowling.view.output.OutputView;

public class BowlingMain {
    public static void main(final String[] args) {
        final String userName = InputView.plzEnterUserName();
        final User user = User.of(userName);
        
        final BowlingGame bowlingGame = BowlingGame.of(user);
        final ResultDTO emptyResultDTO = bowlingGame.getResultDTO();
        OutputView.printResult(emptyResultDTO);
        
        while (bowlingGame.isNotFinished()) {
            final int nowFrameNo = bowlingGame.nowFrameNo();
            final int fallenPins = InputView.plzEnterFallenPins(nowFrameNo);
            final ResultDTO resultDTO = bowlingGame.pitch(Pins.of(fallenPins));
            OutputView.printResult(resultDTO);
        }
    }
}
