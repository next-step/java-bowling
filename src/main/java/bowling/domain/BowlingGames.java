package bowling.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BowlingGames {
    private static final int INIT_TURN = 0;

    private final List<BowlingGame> bowlingGames;
    private int playerTurnIndex;

    public BowlingGames(List<String> players) {
        bowlingGames = players.stream()
                .map(BowlingGame::new)
                .collect(Collectors.toList());
        playerTurnIndex = INIT_TURN;
    }

    public boolean isEnd() {
        return bowlingGames.stream().allMatch(BowlingGame::isEnd);
    }

    public void pitch(final int countOfPins) {
        BowlingGame bowlingGame = currentPlayerFrames();
        if (bowlingGame.pitch(countOfPins)) {
            playerTurnIndex = (playerTurnIndex + 1) % bowlingGames.size();
        }
    }

    public BowlingGame currentPlayerFrames() {
        return bowlingGames.get(playerTurnIndex);
    }

    public List<BowlingGame> games() {
        return Collections.unmodifiableList(bowlingGames);
    }
}
