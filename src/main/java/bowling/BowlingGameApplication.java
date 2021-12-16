package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.Player;
import bowling.strategy.RandomPitchNumberStrategy;
import bowling.view.InputView;

public class BowlingGameApplication {
    public static void main(String[] args) {
        String name = InputView.inputPlayerName();
        Player player = Player.from(name);

        BowlingGame game = BowlingGame.init();
        game.run(player, new RandomPitchNumberStrategy());
    }
}
