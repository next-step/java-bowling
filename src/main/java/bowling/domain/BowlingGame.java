package bowling.domain;

import java.util.List;

public class BowlingGame {
    private final List<Frames> bowlingGame;

    public BowlingGame(List<Frames> bowlingGame) {
        this.bowlingGame = bowlingGame;
    }

    public static BowlingGame of(List<Frames> bowlingGame) {
        return new BowlingGame(bowlingGame);
    }

    public boolean isRoundEnd(int round) {
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
}
