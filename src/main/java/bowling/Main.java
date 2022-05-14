package bowling;

import bowling.domain.frame.Frames;
import bowling.domain.game.Bowling;
import bowling.domain.game.Player;
import bowling.ui.InputView;
import bowling.ui.OutputView;

import static bowling.domain.frame.NormalFrameNo.MIN_NORMAL_FRAME_NO;

public class Main {

    public static void main(String[] args) {
        Player player = InputView.promptPlayer();
        Frames frames = new Frames(InputView.promptPinNo(MIN_NORMAL_FRAME_NO));
        OutputView.printBowling(player, frames);

        Bowling bowling = new Bowling(player, frames);
        while (!bowling.isFinished()) {
            bowling.addPin(InputView.promptPinNo(bowling.currentFrame()));
            OutputView.printBowling(player, frames);
        }
    }

}
