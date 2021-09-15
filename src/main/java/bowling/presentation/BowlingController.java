package bowling.presentation;

import bowling.domain.Player;
import bowling.domain.frame.Frames;
import bowling.presentation.input.PlayerInputView;
import bowling.presentation.output.FrameOutputView;

public class BowlingController {

    private BowlingController() {
    }

    public static BowlingController create() {
        return new BowlingController();
    }

    public void execute() {
        Player player = Player.from(PlayerInputView.create().input());
        printBowlingStart(player);

        Frames frames = Frames.create();
        frames.execute(player);
    }

    private void printBowlingStart(Player player) {
        FrameOutputView.create().print(player);
    }


}
