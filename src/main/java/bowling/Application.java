package bowling;

import bowling.domain.player.Player;
import bowling.view.InputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();

        final Player player = inputView.inputPlayerName();
    }
}
