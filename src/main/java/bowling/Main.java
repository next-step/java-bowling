package bowling;

import bowling.domain.BowlingGames;
import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        int playerNumber = InputView.playerNumberView();
        List<Player> player = InputView.playerNameView(playerNumber);
        BowlingGames bowlingGames = BowlingGames.initialize(player);

        playBowling(bowlingGames);
    }

    private static void playBowling(BowlingGames bowlingGames) {
        while (!bowlingGames.isFinish()) {
            ResultView.bowlingResultView(bowlingGames);
            loopBowlingResult(bowlingGames);
        }
        ResultView.bowlingResultView(bowlingGames);
    }

    private static void loopBowlingResult(BowlingGames bowlingGames) {
        try {
            validatePlayerName(bowlingGames);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            loopBowlingResult(bowlingGames);
        }
    }
    private static void validatePlayerName(BowlingGames bowlingGames) {
        Player currentPlayer = bowlingGames.currentPlayer();
        int hit = InputView.hitCountView(currentPlayer);
        bowlingGames.bowl(hit, currentPlayer);
    }
}
