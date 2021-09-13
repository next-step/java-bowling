package bowling.domain;

import java.util.List;

public class BowlingGame {
    public static final int FIRST_ROUND = 0;

    private List<Frames> bowlingGame;
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

    public Frames currentGame(int playerIndex) {
        return bowlingGame.get(playerIndex);
    }

    public Frame currentGameFrame(int playerIndex) {
        return currentGame(playerIndex).currentFrame().next();
    }

    public void checkRound() {
        if (isRoundEnd()) {
            round++;
        }
    }

    public void bowl(int index, int score) {
        Frame frame = currentGameFrame(index);

        Pins pins = Pins.of(score);
        frame.bowl(pins);

        currentGame(index).add(frame);
    }

    public boolean isBowling(int index) {
        Frame frame = currentGameFrame(index);

        if (!frame.isFinish() && frame.getFrameNumber() - 1 == round) {
            return true;
        }
        return false;
    }
}
