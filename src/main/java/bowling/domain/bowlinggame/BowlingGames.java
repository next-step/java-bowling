package bowling.domain.bowlinggame;

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

    public void addNextFrames() {
        bowlingGames.forEach(BowlingGame::addNextFrame);
    }

    public boolean isAllGameOver() {
        return bowlingGames.stream()
                .allMatch(BowlingGame::isGameOver);
    }

    public int size() {
        return bowlingGames.size();
    }

    public boolean isPlayable(int index) {
        return bowlingGames.get(index).isCurrentFramePlayable();
    }

    public BowlingGame findBowlingGame(int index) {
        return bowlingGames.get(index);
    }
}
