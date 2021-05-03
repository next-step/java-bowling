package bowling.domain;

import java.util.List;
import java.util.stream.Collectors;

public class BowlingGames {
    private final List<BowlingGame> bowlingGames;
    private FrameNumber currentFrame;

    public BowlingGames(FrameNumber currentFrame, List<BowlingGame> bowlingGames) {
        this.currentFrame = currentFrame;
        this.bowlingGames = bowlingGames;
    }

    public BowlingGames(Players players) {
        currentFrame = new FrameNumber(1);
        bowlingGames = players.players()
                .stream()
                .map(player -> new BowlingGame(player))
                .collect(Collectors.toList());
    }

    public void roll(Pinfall pinfall) {
        currentBowlingGame().roll(pinfall);
    }

    public boolean isDone() {
        return bowlingGames.stream()
                .map(BowlingGame::isDone)
                .reduce(Boolean::logicalAnd)
                .orElse(false);
    }

    public Player currentPlayer() {
        return currentBowlingGame().player();
    }

    public FrameNumber currentFrameNumber() {
        return currentBowlingGame().frameNumber();
    }

    public List<BowlingResult> results() {
        return bowlingGames.stream()
                .map(BowlingGame::result)
                .collect(Collectors.toList());
    }

    private BowlingGame currentBowlingGame() {
        if (currentFrame.isLast()) {
            return notFinishedBowlingGame();
        }

        return currentFrameBowlingGame();
    }

    private BowlingGame currentFrameBowlingGame() {
        BowlingGame curremtBowlingGame = bowlingGames.stream()
                .filter(bowlingGame -> bowlingGame.frameNumber().equals(currentFrame))
                .findFirst()
                .orElse(firstGame());

        currentFrame = curremtBowlingGame.frameNumber();
        return curremtBowlingGame;
    }

    private BowlingGame notFinishedBowlingGame() {
        return bowlingGames.stream()
                .filter(bowlingGame -> !bowlingGame.isDone())
                .findFirst()
                .orElse(firstGame());
    }

    private BowlingGame firstGame() {
        return bowlingGames.get(0);
    }
}
