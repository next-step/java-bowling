package bowling;

import bowling.domain.frame.Frames;
import bowling.domain.player.Player;
import bowling.view.InputView;

public class BowlingApplication {
    public static void main(String[] args) {
        Player player = InputView.getPlayer();
        Frames frames = Frames.init();
    }
}
