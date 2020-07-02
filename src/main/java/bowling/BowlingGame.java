package bowling;

import bowling.domain.Player;
import bowling.domain.Frames;
import bowling.ui.InputView;
import bowling.ui.ResultFrameDto;
import bowling.ui.ResultView;

public class BowlingGame {

    public static void main(String[] args) {
        Player player = new Player(InputView.getPlayerName());
        Frames frames = new Frames(player);

        ResultView.printBoard(new ResultFrameDto(frames));

        while (true) {
            boolean isFinish = frames.bowling(InputView.getPin(frames.getCurrentFrameNo()));
            ResultView.printBoard(new ResultFrameDto(frames));

            if (isFinish) {
                break;
            }
        }
    }
}
