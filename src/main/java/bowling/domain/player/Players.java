package bowling.domain.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import bowling.domain.frame.Frames;
import bowling.domain.score.TotalScoreBoard;

public class Players {
    private static final String PLAYER_NAMES_IS_NULL_EXCEPTION_STATEMENT = "플레이어의 이름 데이터가 널입니다";

    private final List<Player> players;
    private final TotalScoreBoard totalScoreBoard;

    private Players(List<String> playerNames) {
        validate(playerNames);
        players = new ArrayList<>();
        playerNames.forEach(playerName -> addPlayer(
            Player.from(playerName, Frames.from(new ArrayList<>())))
        );
        totalScoreBoard = TotalScoreBoard.from(this);
    }

    public static Players init(List<String> playerNames) {
        return new Players(playerNames);
    }

    private void validate(List<String> playerNames) {
        if (Objects.isNull(playerNames)) {
            throw new IllegalArgumentException(PLAYER_NAMES_IS_NULL_EXCEPTION_STATEMENT);
        }
    }

    private void addPlayer(Player player) {
        players.add(player);
    }

    public boolean isGameFinish() {
        long finishedCount = players.stream().filter(i -> i.frames().isFinish()).count();
        return finishedCount == players.size();
    }

    public List<String> names() {
        return players.stream()
            .map(Player::name)
            .collect(Collectors.toList());
    }

    public List<List<String>> results() {
        return players.stream()
            .map(Player::results)
            .collect(Collectors.toList());
    }

    public int numberOfPlayers() {
        return players.size();
    }

    public List<Player> players() {
        return players;
    }

    public TotalScoreBoard totalScoreBoard() {
        return totalScoreBoard;
    }
}
