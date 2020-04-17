package bowling;

import bowling.bowlinggame.BowlingGames;
import bowling.domain.frame.Frame;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingApplication {
    public static void main(String[] args) {
        BowlingGames bowlingGames = BowlingController.ready(InputView.inputPlayers());
        ResultView.printReadyToBowlingGame(bowlingGames);

        while (!bowlingGames.isOverAllGames()) {
            playBowlingGames(bowlingGames);
        }
    }

    private static void playBowlingGames(BowlingGames bowlingGames) {
        for (int i = 0; i < bowlingGames.size(); i++) {
            bowlingGames.addNextFrame(i);
            playBowling(bowlingGames, i);
        }
    }

    private static void playBowling(BowlingGames bowlingGames, int index) {
        Frame frame = bowlingGames.getLastFrame(index);
        while (frame.isPlayable()) {
            frame.addScore(InputView.relaseBowling(bowlingGames.getPlayer(index)));
            ResultView.printBowlingScores(bowlingGames);
        }
    }
}
