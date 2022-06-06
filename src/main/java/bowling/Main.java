package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.ResultView;

public class Main {

    public static void main(String[] args) {
        Player player = InputView.playerNameView();
        BowlingGame bowlingGame = new BowlingGame(player);

        playBowling(bowlingGame);
    }

    private static void playBowling(BowlingGame bowlingGame) {
        while (!bowlingGame.isFinish()) {
            ResultView.bowlingResultView(bowlingGame);
            bowlingGame = loopBowlingResult(bowlingGame);
        }
        ResultView.bowlingResultView(bowlingGame);
    }

    private static BowlingGame loopBowlingResult(BowlingGame bowlingGame) {
        try {
            return validatePlayerName(bowlingGame);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return loopBowlingResult(bowlingGame);
        }
    }
    private static BowlingGame validatePlayerName(BowlingGame bowlingGame) {
        int hit = InputView.hitCountView(bowlingGame.currentFrame());
        bowlingGame.bowling(hit);
        return bowlingGame;
    }
}
