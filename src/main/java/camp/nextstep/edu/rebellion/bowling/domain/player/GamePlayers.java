package camp.nextstep.edu.rebellion.bowling.domain.player;

import camp.nextstep.edu.rebellion.bowling.domain.game.BowlingGame;
import camp.nextstep.edu.rebellion.bowling.domain.score.BowlingGameScoreBoard;

import java.util.List;
import java.util.stream.Collectors;

public class GamePlayers {
    private final List<Player> players;
    private final List<BowlingGame> bowlingGames;

    private GamePlayers(List<String> participants) {
        this.players = participants.stream()
                .map(Player::new)
                .collect(Collectors.toList());
        this.bowlingGames = players.stream()
                .map(BowlingGame::start)
                .collect(Collectors.toList());
    }

    public static GamePlayers join(List<String> participants) {
        return new GamePlayers(participants);
    }

    public int getNumberOfPlayers() {
        return players.size();
    }

    public String findNameByOrdinal(int ordinal) {
        return players.get(ordinal)
                .getName();
    }

    public BowlingGame choose(int index) {
        return bowlingGames.get(index);
    }

    public BowlingGameScoreBoard getScoreBoard() {
        return bowlingGames
                .stream()
                .map(BowlingGame::getScoreBoard)
                .collect(Collectors.collectingAndThen(Collectors.toList(),
                        BowlingGameScoreBoard::new));
    }
}
