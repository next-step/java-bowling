package bowling;

import bowling.domain.game.Game;
import bowling.domain.player.Player;
import bowling.domain.view.InputView;

public class Main {
    public static void main(String[] args) {
        Player player = new Player(InputView.inputPlayerName());
        Game game = new Game(player);
        game.startGame();

    }
}
