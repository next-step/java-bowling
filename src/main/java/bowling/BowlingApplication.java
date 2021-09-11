package bowling;

import bowling.domain.*;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.ArrayList;
import java.util.List;

public class BowlingApplication {
    public static void main(String args[]) {
        int count = InputView.getPlayerCount();
        Players players = Players.of(InputView.getPlayerNames(count));
        BowlingGame bowlingGame = BowlingGame.of(initFrames(players));

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

    private static void gamePlay(Players players, BowlingGame bowlingGame, int round) {
        for (int index = 0; index < players.size(); index++) {
            bowling(players, bowlingGame, round, index);
        }
    }

    private static void bowling(Players players, BowlingGame bowlingGame, int round, int index) {
        Frame frame = bowlingGame.currentFrame(index).next();

        if (!frame.isFinish() && frame.getFrameNumber() - 1 == round) {
            int score = InputView.getFrameScore(players.name(index));
            Pins pins = Pins.of(score);
            frame.bowl(pins);

            Frames currentGame = bowlingGame.get(index);
            currentGame.add(frame);

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
