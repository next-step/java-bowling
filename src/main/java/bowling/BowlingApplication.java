package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.Frame;
import bowling.domain.Frames;
import bowling.domain.Pins;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.ArrayList;
import java.util.List;

import static bowling.view.ResultView.printBowlingGame;

public class BowlingApplication {
    public static void main(String args[]) {

        int count = InputView.getPlayerCount();
        List<String> players = InputView.getPlayerNames(count);
        List<Frames> frames = initFrames(players);

        BowlingGame bowlingGame = BowlingGame.of(frames);

        int round = 0;

        while (!bowlingGame.isEnd()) {
            gamePlay(players, bowlingGame, round);
            round = getRound(bowlingGame, round);
        }

    }

    private static int getRound(BowlingGame bowlingGame, int round) {
        if (bowlingGame.isRoundEnd(round)) {
            round++;
        }
        return round;
    }

    private static void gamePlay(List<String> players, BowlingGame bowlingGame, int round) {
        for (int i = 0; i < players.size(); i++) {
            bowlling(players, bowlingGame, round, i);
        }
    }

    private static void bowlling(List<String> players, BowlingGame bowlingGame, int round, int i) {
        Frame frame = bowlingGame.currentFrame(i).next();

        if (!frame.isFinish() && frame.getFrameNumber() - 1 == round) {
            frame.bowl(Pins.of(InputView.getFrameScore(players.get(i))));
            bowlingGame.get(i).add(frame);

            ResultView.printTitle();
            ResultView.printBowlingGame(players, bowlingGame);
        }
    }

    private static List<Frames> initFrames(List<String> players) {
        List<Frames> frames = new ArrayList<>();

        for (int i = 0; i < players.size(); i++) {
            frames.add(Frames.newInstance());
        }
        return frames;
    }
}
