package bowling.domain;

import java.util.Collections;
import java.util.List;

public class BowlingGames {

    private final int LAST_PLAYER_TURN_INDEX;

    private final List<BowlingGame> bowlingGames;

    private int playerTurnIndex = 0;

    private BowlingGames(List<BowlingGame> bowlingGames) {
        this.bowlingGames = Collections.unmodifiableList(bowlingGames);
        LAST_PLAYER_TURN_INDEX = initLastPlayerTurnIndex(bowlingGames);
    }

    private int initLastPlayerTurnIndex(List<BowlingGame> bowlingGames) {
        return bowlingGames.size() - 1;
    }

    public static BowlingGames create(List<BowlingGame> bowlingGames) {
        return new BowlingGames(bowlingGames);
    }

    public Player currentPlayer() {
        BowlingGame bowlingGame = bowlingGames.get(playerTurnIndex);
        return bowlingGame.player();
    }

    public void play(Pins pins) {
        BowlingGame bowlingGame = bowlingGames.get(playerTurnIndex);
        bowlingGame.pitch(pins);

        if (bowlingGame.isCurrentFrameEnd()) {
            bowlingGame.nextRound();
            changeNextPlayerTurn();
        }

    }

    private void changeNextPlayerTurn() {
        int nextPlayerTurnIndex = playerTurnIndex + 1;
        if(nextPlayerTurnIndex > LAST_PLAYER_TURN_INDEX) {
            nextPlayerTurnIndex = 0;
        }
        playerTurnIndex = nextPlayerTurnIndex;
    }

    public boolean isRunning() {
        for (BowlingGame bowlingGame : bowlingGames) {
            if(bowlingGame.isRunning()) {
                return true;
            }
        }
        return false;
    }

    public List<BowlingGame> bowlingGames() {
        return bowlingGames;
    }
}
