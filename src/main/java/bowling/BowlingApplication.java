package bowling;

import bowling.domain.bowlinggame.BowlingGame;
import bowling.domain.bowlinggame.BowlingGames;
import bowling.view.InputView;
import bowling.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class BowlingApplication {

    public static void main(String[] args) {
        int playerCount = InputView.inputPlayerCount();
        List<String> playerNames = new ArrayList<>();
        for (int i = 0; i < playerCount; i++) {
            playerNames.add(InputView.inputPlayerName(i));
        }
        OutputView.outputDefaultFrame(playerNames);

        BowlingGames bowlingGames = new BowlingGames(playerNames);
        while (!bowlingGames.isAllGameOver()) {
            bowlingGames.addNextFrames();
            playBowlingGames(bowlingGames);
        }

    }

    private static void playBowlingGames(BowlingGames bowlingGames) {
        for (int i = 0; i < bowlingGames.size(); i++) {
            writeBowlingGamePoint(bowlingGames, i);
        }
    }

    private static void writeBowlingGamePoint(BowlingGames bowlingGames, int index) {
        while (bowlingGames.isPlayable(index)) {
            BowlingGame bowlingGame = bowlingGames.findBowlingGame(index);
            int point = InputView.inputScore(bowlingGame.getPlayer());
            bowlingGame.writePoint(point);
            OutputView.outputBowlingGames(bowlingGames);
        }
    }
}
