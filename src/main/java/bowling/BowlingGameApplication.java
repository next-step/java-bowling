package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.Player;
import bowling.dto.BowlingGameDto;
import bowling.dto.PlayerDto;
import bowling.views.InputView;
import bowling.views.OutputView;

public class BowlingGameApplication {

    public static void main(String[] args) {
        try {
            runApplication();
        } catch (Exception e) {
            OutputView.printError(e);
        }
    }

    private static void runApplication() {
        Player player = new Player(InputView.inputPlayerName());
        BowlingGame game = new BowlingGame();
        OutputView.printBowlingGame(PlayerDto.from(player), BowlingGameDto.from(game));

        while (!game.isEnded()) {
            int hit = InputView.inputNumberOfPinsHit(game.getNumberOfCurrentFrame());
            game.hit(hit);
            OutputView.printBowlingGame(PlayerDto.from(player), BowlingGameDto.from(game));
        }
    }

}
