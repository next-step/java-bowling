package bowling;

import bowling.bowler.Bowlers;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingApplication {

    public static void main(String[] args) {
        int bowlersNumber = InputView.inputPlayerNumber();
        Bowlers bowlers = Bowlers.from(InputView.getBowlers(bowlersNumber));

        BowlingGames bowlingGames = BowlingGames.start(bowlers.getBowlers());

        ResultView.printGameBoard(bowlingGames);

        while (!bowlingGames.isEnd()) {
            playBowling(bowlingGames);
        }

    }

    private static void playBowling(BowlingGames bowlingGames) {
        for (BowlingGame bowlingGame : bowlingGames.getBowlingGames()) {
            bowlByBowler(bowlingGames, bowlingGame);
        }
    }

    private static void bowlByBowler(BowlingGames bowlingGames, BowlingGame bowlingGame) {
        int currentFrameNumber = bowlingGame.getFrameNumber();

        while (!bowlingGame.isCurrentFrameFinish(currentFrameNumber)) {
            String bowlerName = bowlingGame.getBowlerName();
            bowlingGame.bowl(InputView.inputPins(bowlerName));

            ResultView.printGameBoard(bowlingGames);
        }
    }

}
