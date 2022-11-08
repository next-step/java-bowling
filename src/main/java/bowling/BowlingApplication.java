package bowling;

import bowling.domain.BowlingGame;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.List;

public class BowlingApplication {
    public static void main(String[] args) {
        List<String> names = InputView.inputPlayerNames();
        BowlingGame game = new BowlingGame(names);

        ResultView.printScoreBoard(game);

        int frameIndex = 0;
        while (!game.isEndGame()) {
            doGame(game, frameIndex);
            frameIndex++;
        }
    }

    private static void doGame(BowlingGame game, int frameIndex) {
        int laneIndex = 0;
        do {
            doFrames(game, frameIndex, laneIndex);
            laneIndex++;
        } while (!game.isEndFrames(frameIndex));
    }

    private static void doFrames(BowlingGame game, int frameIndex, int laneIndex) {
        do {
            doFrame(game, laneIndex);
        } while (!game.isEndPlayerFrame(frameIndex, laneIndex));
    }

    private static void doFrame(BowlingGame game, int laneIndex) {
        int inputDownPinCount;
        inputDownPinCount = InputView.inputDownPinCount(game.getPlayerName(laneIndex));
        game.doGame(laneIndex, inputDownPinCount);
        ResultView.printScoreBoard(game);
    }

}