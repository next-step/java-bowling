package bowling.domain;

import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class BowlingGames {

    private static final int NEXT_INDEX = 1;
    private static final int LAST_INDEX_GAP = 1;

    private final List<BowlingGame> games;
    private int currentPlayerIndex;

    public BowlingGames(List<String> names) {
        this.games = names.stream()
                          .map(BowlingGame::new)
                          .collect(toList());

        this.currentPlayerIndex = 0;
    }

    public List<BowlingGame> getGames() {
        return Collections.unmodifiableList(games);
    }

    public void swing(int score) {

        BowlingGame currentGame = games.get(currentPlayerIndex);
        boolean isCurrentGameFrameEnd = currentGame.swing(score);

        if (isCurrentGameFrameEnd) {
            nextPlayersTurn();
        }
    }

    private void nextPlayersTurn() {
        currentPlayerIndex = (currentPlayerIndex + NEXT_INDEX) % games.size();
    }

    public boolean isNotEnd() {
        return !games.stream()
                     .allMatch(BowlingGame::isEnd);
    }

    private BowlingGame lastGame() {
        return games.get(games.size() - LAST_INDEX_GAP);
    }

    public BowlingGame getCurrentGame() {
        return games.get(currentPlayerIndex);
    }
}
