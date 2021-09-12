package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.Frames;
import bowling.domain.Players;
import bowling.view.InputView;

import java.util.ArrayList;
import java.util.List;

public class BowlingApplication {
    public static void main(String args[]) {
        int count = InputView.getPlayerCount();
        Players players = Players.of(InputView.getPlayerNames(count));
        BowlingGame bowlingGame = BowlingGame.of(initFrames(players), 0);

        while (!bowlingGame.isEnd()) {
            bowlingGame.gamePlay(players, bowlingGame);
            bowlingGame.checkRound();
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
