package bowling.presentation;

import bowling.domain.Player;
import bowling.presentation.input.PlayerInputView;
import bowling.presentation.output.FrameOutputView;
import bowling.service.FramesService;

public class BowlingController {

    private BowlingController() {
    }

    public static BowlingController create() {
        return new BowlingController();
    }

    public void execute() {
        Player player = Player.from(PlayerInputView.create().input());
        printBowlingStart(player);
        FramesService.create().execute(player);
    }

    private void printBowlingStart(Player player) {
        FrameOutputView.create().print(player);
    }


}
