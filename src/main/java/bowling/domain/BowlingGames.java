package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BowlingGames {
    private final List<BowlingGame> bowlingGames;
    private FrameNumber currentFrame = new FrameNumber(1);

    public BowlingGames(List<BowlingGame> bowlingGames) {
        this.bowlingGames = bowlingGames;
    }

    public BowlingGames(Players players) {
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
        BowlingGame curremtBowlingGame = bowlingGames.stream()
                .filter(bowlingGame -> bowlingGame.frameNumber().equals(currentFrame))
                .findFirst()
                .orElse(firstGame());
        currentFrame = curremtBowlingGame.frameNumber();
        return curremtBowlingGame;
    }

    private BowlingGame firstGame() {
        return bowlingGames.get(0);
    }
}
