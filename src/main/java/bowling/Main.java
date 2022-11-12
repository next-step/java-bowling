package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.Lane;
import bowling.view.InputView;
import bowling.view.ResultView;

public class Main {
    public static void main(String[] args) {
        int playerCount = InputView.scanPlayerCount();
        BowlingGame bowlingGame = BowlingGame.of(InputView.scanPlayerNames(playerCount));
        ResultView.printFrames(bowlingGame);

        while (!bowlingGame.isAllFinished()) {
            playBowling(bowlingGame);
        }

        InputView.closeScan();
    }

    private static void playBowling(BowlingGame bowlingGame) {
        for (Lane lane : bowlingGame.getLanes()) {
            bowlInAFrame(bowlingGame, lane);
        }
    }

    private static void bowlInAFrame(BowlingGame bowlingGame, Lane lane) {
        bowl(bowlingGame, lane);
        while (!lane.isCurrentFrameFinished()) {
            bowl(bowlingGame, lane);
        }
    }

    private static void bowl(BowlingGame bowlingGame, Lane lane) {
        int fallenPins = InputView.scanFallenPinCount(lane.getPlayerName());
        tryBowling(lane, fallenPins);
        ResultView.printFrames(bowlingGame);
    }

    private static void tryBowling(Lane lane, int fallenPins) {
        try {
            lane.bowl(fallenPins);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
