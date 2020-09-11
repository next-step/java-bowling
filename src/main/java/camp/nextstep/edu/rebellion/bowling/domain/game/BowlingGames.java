package camp.nextstep.edu.rebellion.bowling.domain.game;

import camp.nextstep.edu.rebellion.bowling.domain.player.GamePlayers;
import camp.nextstep.edu.rebellion.bowling.domain.score.BowlingGameScoreBoard;

public class BowlingGames {
    private final GamePlayers gamePlayers;
    private final Turn turn;

    private BowlingGames(GamePlayers gamePlayers) {
        this.gamePlayers = gamePlayers;
        this.turn = Turn.setup(gamePlayers.getNumberOfPlayers());
    }

    public static BowlingGames start(GamePlayers gamePlayers) {
        return new BowlingGames(gamePlayers);
    }

    public void record(int hits) {
        BowlingGame currentGame = gamePlayers.choose(turn.have());

        currentGame.record(hits);

        if (currentGame.meetHandOverCondition()) {
            turn.handOver();
        }
    }

    public String currentPlayerName() {
        return gamePlayers.findNameByOrdinal(turn.have());
    }

    public boolean hasNext() {
        for (int i = turn.have(); i < gamePlayers.getNumberOfPlayers(); i++) {
            if (gamePlayers.choose(i).hasNext()) {
                return true;
            }
            turn.handOver();
        }
        return false;
    }

    public BowlingGameScoreBoard getScoreStatus() {
        return gamePlayers.getScoreBoard();
    }
}
