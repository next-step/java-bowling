package bowling;

import bowling.domain.*;
import bowling.ui.InputView;
import bowling.ui.ResultFrameDto;
import bowling.ui.ResultView;

public class BowlingGame {

    public static void main(String[] args) {
        BowlingStrategy bowlingStrategy = new RandomBowlingStrategy();
        Player player = new Player(InputView.getPlayerName());
        Frames frames = new Frames(player);

        ResultView.printBoard(new ResultFrameDto(frames));

        FrameState frameState = FrameState.ofNew();
        while (true) {
            Pin nextPin = bowlingStrategy.nextPin(frameState.getRemainPinCount());
            ResultView.printNextPin(frames.getCurrentFrameNo(), nextPin);

            frameState = frames.bowling(nextPin);
            ResultView.printBoard(new ResultFrameDto(frames));

            if (frameState.isFinish()) {
                break;
            }
        }
    }
}
