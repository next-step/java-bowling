package bowling.domain;

import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.List;

public class BowlingGame {
    private final List<Frames> bowlingGame;
    private int round;

    public BowlingGame(List<Frames> bowlingGame, int round) {
        this.bowlingGame = bowlingGame;
        this.round = round;
    }

    public static BowlingGame of(List<Frames> bowlingGame, int round) {
        return new BowlingGame(bowlingGame, round);
    }

    public boolean isRoundEnd() {
        return bowlingGame.stream()
                .allMatch(frames -> frames.isRoundEnd(round));
    }

    public boolean isEnd() {
        return bowlingGame.stream()
                .allMatch(frames -> frames.isEnd());
    }

    public int size() {
        return bowlingGame.size();
    }

    public Frames get(int playerIndex) {
        return bowlingGame.get(playerIndex);
    }

    public Frame frame(int playerIndex, int frameNumber) {
        return get(playerIndex).get(frameNumber);
    }

    public Frame currentFrame(int playerIndex) {
        return get(playerIndex).currentFrame();
    }


    public int checkRound() {
        if (isRoundEnd()) {
            round++;
        }
        return round;
    }

    public void gamePlay(Players players, BowlingGame bowlingGame) {
        for (int index = 0; index < players.size(); index++) {
            bowling(players, bowlingGame, index);
        }
    }

    private void bowling(Players players, BowlingGame bowlingGame, int index) {
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
}
