package bowling;

import bowling.domain.Player;
import bowling.view.InputView;

public final class Bowling {
    private final static InputView INPUT_VIEW;

    static {
        INPUT_VIEW = InputView.getInstance();
    }

    public static void main(String[] args) {
        final Player player = getPlayer();
        // Frames frames.initialize();

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
