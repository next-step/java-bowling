package bowling;

import bowling.domain.BowlingGame;
import bowling.dto.BowlingGameDto;
import bowling.utils.BowlingGameGenerator;
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
        String playerName = InputView.inputPlayerName();
        BowlingGame game = BowlingGameGenerator.createBowlingGame();
        OutputView.printBowlingGame(playerName, BowlingGameDto.from(game));

        while (!game.isEnded()) {
            int hit = InputView.inputNumberOfPinsHit(game.getIndexOfCurrentFrame(), game.getRemainedPins());
            game.hit(hit);
            OutputView.printBowlingGame(playerName, BowlingGameDto.from(game));
        }
    }

}
