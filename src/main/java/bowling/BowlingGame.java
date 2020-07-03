package bowling;

import bowling.domain.*;
import bowling.domain.state.State;
import bowling.ui.InputView;
import bowling.ui.ResultFrameDto;
import bowling.ui.ResultView;

public class BowlingGame {

    public static void main(String[] args) {
        BowlingStrategy bowlingStrategy = new RandomBowlingStrategy();
        Player player = new Player(InputView.getPlayerName());
        Frames frames = new Frames(player);

        ResultView.printBoard(new ResultFrameDto(frames));

        State state = State.ofNew();
        while (true) {
            Pin nextPin = bowlingStrategy.nextPin(state.getRemainPin());
            ResultView.printNextPin(frames.getCurrentFrameNo(), nextPin);

            state = frames.bowling(nextPin);
            ResultView.printBoard(new ResultFrameDto(frames));

            if (state.isFinish()) {
                break;
            }
        }
    }
}
