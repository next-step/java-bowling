package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.Frames;
import bowling.domain.Players;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.ArrayList;
import java.util.List;

public class BowlingApplication {
    public static void main(String args[]) {
        int count = InputView.getPlayerCount();
        Players players = Players.of(InputView.getPlayerNames(count));
        BowlingGame bowlingGame = BowlingGame.of(initFrames(players), BowlingGame.FIRST_ROUND);

        while (!bowlingGame.isEnd()) {
            playGame(players, bowlingGame);
            bowlingGame.checkRound();
        }
    }

    private static void playGame(Players players, BowlingGame bowlingGame) {
        for (int index = 0; index < players.size(); index++) {
            bowling(players, bowlingGame, index);
        }
    }

    private static void bowling(Players players, BowlingGame bowlingGame, int index) {
        if (bowlingGame.isBowling(index)) {
            int score = InputView.getFrameScore(players.name(index));

            bowlingGame.bowl(index, score);

            ResultView.printTitle();
            ResultView.printBowlingGame(players, bowlingGame);
        }
    }

    private static List<Frames> initFrames(Players players) {
        List<Frames> frames = new ArrayList<>();

        for (int i = 0; i < players.size(); i++) {
            frames.add(Frames.newInstance());
        }

        return frames;
    }
}
