package bowling.domain;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BowlingGames {
    private final List<BowlingGame> bowlingGames;

    public BowlingGames(Players players) {
        this.bowlingGames = createBowlingGame(players);
    }

    private List<BowlingGame> createBowlingGame(Players players) {
        return players.getPlayers()
                .stream()
                .map(BowlingGame::new)
                .collect(Collectors.toList());
    }

    public boolean isEndGame() {
        return bowlingGames.stream()
                .allMatch(BowlingGame::isEndGame);
    }

    public BowlingGame getCurrentGame() {
        return bowlingGames.stream()
                .filter(bowlingGame -> !bowlingGame.isEndFrame(getCurrentFrameNumber()))
                .findFirst()
                .orElseThrow(CannotGetCurrentGameException::new);
    }

    private int getCurrentFrameNumber() {
        return bowlingGames.stream()
                .map(BowlingGame::getCurrentFrameNumber)
                .min(Comparator.naturalOrder())
                .orElseThrow(CannotGetCurrentFrameNumberException::new);
    }

    public List<BowlingGame> getBowlingGames() {
        return bowlingGames;
    }
}
