package bowling.domain.bowlinggame;

import bowling.domain.player.Player;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class BowlingGames {
    private final List<BowlingGame> bowlingGames;

    public BowlingGames(List<String> playerNames) {
        validatePlayerNames(playerNames);
        this.bowlingGames = playerNames.stream()
                .map(BowlingGame::new)
                .collect(collectingAndThen(toList(), Collections::unmodifiableList));
    }

    private void validatePlayerNames(List<String> playerNames) {
        if (Objects.isNull(playerNames) || playerNames.isEmpty()) {
            throw new IllegalArgumentException("playerNames is not empty");
        }
    }

    public boolean isAllGameOver() {
        return bowlingGames.stream()
                .allMatch(BowlingGame::isGameOver);
    }

    public int size() {
        return bowlingGames.size();
    }

    public BowlingGame findBowlingGame(int index) {
        return bowlingGames.get(index);
    }

    public void writePoint(int point) {
        addFrames();
        BowlingGame bowlingGame = findCurrentBowlingGame();
        bowlingGame.writePoint(point);
    }

    private void addFrames() {
        if (isEmptyFirstFrame() || !isAvailablePlayBowlingGame()) {
            bowlingGames.forEach(BowlingGame::addNextFrame);
        }
    }

    public Player currentPlayer() {
        if (isEmptyFirstFrame() || !isAvailablePlayBowlingGame()) {
            return bowlingGames.get(0).getPlayer();
        }
        return findCurrentBowlingGame().getPlayer();
    }

    private BowlingGame findCurrentBowlingGame() {
        return bowlingGames.stream()
                .filter(bowlingGame -> bowlingGame.isCurrentFramePlayable())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("not found bowlingGame"));
    }

    private boolean isEmptyFirstFrame() {
        return bowlingGames.stream()
                .allMatch(BowlingGame::isEmptyFrames);
    }

    private boolean isAvailablePlayBowlingGame() {
        return bowlingGames.stream()
                .anyMatch(BowlingGame::isCurrentFramePlayable);
    }
}
