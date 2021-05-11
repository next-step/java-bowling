package bowling;

import bowling.domain.player.Player;
import bowling.domain.state.Pins;
import bowling.view.InputView;
import bowling.view.ResultView;

public final class Bowling {

    private static final InputView INPUT_VIEW;
    private static final ResultView RESULT_VIEW;

    static {
        INPUT_VIEW = InputView.getInstance();
        RESULT_VIEW = ResultView.getInstance();
    }

    public static void main(String[] args) {
        Player player = getPlayer();
        RESULT_VIEW.printScoreBoard(player);
        while (!player.isFinish()) {
            playBowl(player);
            RESULT_VIEW.printScoreBoard(player);
        }
        RESULT_VIEW.printResult(player);
    }

    private static final void playBowl(final Player player) {
        try {
            final Pins fallPins = Pins.valueOf(INPUT_VIEW.InputFallCountByConsole(player.sequence()));
            player.bowl(fallPins);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            playBowl(player);
        }
    }

    private static final Player getPlayer() {
        try {
            return Player.from(INPUT_VIEW.InputPlayerNameByConsole());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getPlayer();
        }
    }
}
