package bowling.domain;

import java.util.Collections;
import java.util.List;

public class BowlingGames {

    private static final int FIRST_PLAYER_TURN = 0;

    private final List<BowlingGame> bowlingGames;

    private final int lastPlayerTurnIndex;

    private int playerTurnIndex = 0;

    private BowlingGames(List<BowlingGame> bowlingGames) {
        this.bowlingGames = Collections.unmodifiableList(bowlingGames);
        lastPlayerTurnIndex = initLastPlayerTurnIndex(bowlingGames);
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
        if(nextPlayerTurnIndex > lastPlayerTurnIndex) {
            nextPlayerTurnIndex = FIRST_PLAYER_TURN;
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
