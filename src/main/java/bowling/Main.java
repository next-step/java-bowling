package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.Player;
import bowling.dto.BowlingResult;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        int playerNumber = InputView.playerNumberView();
        List<Player> player = InputView.playerNameView(playerNumber);
        BowlingGame bowlingGame = new BowlingGame(new Player("TOM"));

        playBowling(bowlingGame);
    }

    private static void playBowling(BowlingGame bowlingGame) {
        while (!bowlingGame.isFinish()) {
            ResultView.bowlingResultView(BowlingResult.from(bowlingGame));
            bowlingGame = loopBowlingResult(bowlingGame);
        }
        ResultView.bowlingResultView(BowlingResult.from(bowlingGame));
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
