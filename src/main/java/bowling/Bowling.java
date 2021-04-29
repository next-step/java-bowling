package bowling;

import bowling.domain.Player;
import bowling.view.InputView;

public class Bowling {

    private static final InputView INPUT_VIEW;

    static {
        INPUT_VIEW = InputView.getInstance();
    }

    public static void main(String[] args) {
        Player player = Player.from(INPUT_VIEW.InputUserNameByClient());
    }
}
