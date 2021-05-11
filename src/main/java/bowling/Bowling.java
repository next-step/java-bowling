package bowling;

import bowling.domain.player.Player;
import bowling.domain.state.Pins;
import bowling.view.InputView;

public final class Bowling {

    private static final InputView INPUT_VIEW;

    static {
        INPUT_VIEW = InputView.getInstance();
    }

    public static void main(String[] args) {
        Player player = Player.from(INPUT_VIEW.InputPlayerNameByConsole());
        while (!player.isFinish()) {
            Pins fallPins = Pins.valueOf(INPUT_VIEW.InputFallCountByConsole(player.sequence()));
            player.bowl(fallPins);
        }
    }
}
