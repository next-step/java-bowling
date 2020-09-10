package camp.nextstep.edu.rebellion.bowling.domain.game;

import camp.nextstep.edu.rebellion.bowling.domain.player.Players;
import camp.nextstep.edu.rebellion.bowling.domain.score.BowlingGameScoreBoard;

import java.util.List;
import java.util.stream.Collectors;

public class BowlingGames {
    private final List<BowlingGame> bowlingGames;
    private final Players players;
    private final Turn turn;

    private BowlingGames(Players players) {
        this.bowlingGames = ready(players);
        this.players = players;
        this.turn = Turn.setup(players.getNumberOfPlayers());
    }

    public static BowlingGames start(Players players) {
        return new BowlingGames(players);
    }

    public void record(int hits) {
        BowlingGame currentGame = bowlingGames.get(turn.have());

        currentGame.record(hits);

        if (currentGame.meetPassTurn()) {
            turn.next();
        }
    }

    public String currentPlayerName() {
        return players.findNameByOrdinal(turn.have());
    }

    public boolean hasNext() {
        // TODO 규칙 위반
        // [규칙 1] 한 메서드에 오직 한 단계의 들여쓰기만 한다
        for (int i = turn.have(); i < bowlingGames.size(); i++) {
            if (bowlingGames.get(i).hasNext()) {
                return true;
            }
            turn.next();
        }

        return false;
    }

    public BowlingGameScoreBoard getScoreBoard() {
        return bowlingGames.stream()
                .map(BowlingGame::getScoreBoard)
                .collect(Collectors.collectingAndThen(Collectors.toList(),
                        BowlingGameScoreBoard::new));
    }

    private List<BowlingGame> ready(Players players) {
        return players.getPlayers()
                .stream()
                .map(BowlingGame::start)
                .collect(Collectors.toList());
    }
}
