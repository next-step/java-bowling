package bowling.domain.score;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import bowling.domain.player.Players;

public class TotalScoreBoard {
    private static final String PLAYERS_IS_NULL_EXCEPTION_STATEMENT = "플레이어들 객체가 널입니다";

    private final List<PureScores> pureScoresOfPlayers;
    private final List<List<Integer>> cumulativeScoresOfPlayers;

    private TotalScoreBoard(Players players) {
        validate(players);
        pureScoresOfPlayers = new ArrayList<>();
        cumulativeScoresOfPlayers = new ArrayList<>();
        IntStream.range(0, players.numberOfPlayers()).forEach(
            player -> {
                cumulativeScoresOfPlayers.add(new ArrayList<>());
                pureScoresOfPlayers.add(
                    PureScores.from(players.players().get(player).frames(), cumulativeScoresOfPlayers.get(player)));
            }
        );
    }

    public static TotalScoreBoard from(Players players) {
        return new TotalScoreBoard(players);
    }

    private void validate(Players players) {
        if (Objects.isNull(players)) {
            throw new IllegalArgumentException(PLAYERS_IS_NULL_EXCEPTION_STATEMENT);
        }
    }

    public List<PureScores> pureScoresOfPlayers() {
        return pureScoresOfPlayers;
    }

    public List<List<Integer>> cumulativeScoresOfPlayers() {
        return cumulativeScoresOfPlayers;
    }
}
