package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.Frame;
import bowling.domain.Player;
import bowling.strategy.RandomPitchNumberStrategy;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.List;

public class BowlingGameApplication {
    public static void main(String[] args) {
        String name = InputView.inputPlayerName();
        Player player = Player.from(name);

        BowlingGame game = BowlingGame.init();
        List<Frame> frames = game.run(new RandomPitchNumberStrategy());

        ResultView.showBoard(player, frames);
    }
}
