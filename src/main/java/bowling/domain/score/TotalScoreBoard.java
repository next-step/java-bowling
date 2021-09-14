package bowling.domain.score;

import bowling.domain.frame.AllFrames;
import bowling.domain.player.Players;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class TotalScoreBoard {
    private static final String PLAYERS_IS_NULL_EXCEPTION_STATEMENT = "플레이어들 객체가 널입니다";

    private final List<PureScores> pureScoresOfPlayers;
    private final List<List<Integer>> cumulativeScoresOfPlayers;

    private TotalScoreBoard(Players players, AllFrames allFrames) {
        validate(players);
        pureScoresOfPlayers = new ArrayList<>();
        cumulativeScoresOfPlayers = new ArrayList<>();
        IntStream.range(0, players.numberOfPlayers()).forEach(
            player -> {
                cumulativeScoresOfPlayers.add(new ArrayList<>());
                pureScoresOfPlayers.add(
                    PureScores.from(allFrames.nthFramesOf(player), cumulativeScoresOfPlayers.get(player)));
            }
        );
    }

    public static TotalScoreBoard from(Players players, AllFrames allFrames) {
        return new TotalScoreBoard(players, allFrames);
    }

    private void validate(Players players) {
        if (Objects.isNull(players)) {
            throw new IllegalArgumentException(PLAYERS_IS_NULL_EXCEPTION_STATEMENT);
        }
    }

    public PureScores nthPureScoresOf(int nth) {
        return pureScoresOfPlayers.get(nth);
    }

    public List<List<Integer>> cumulativeScoresOfPlayers() {
        return cumulativeScoresOfPlayers;
    }

    public void updateNthCumulativeScoresOf(int nth, List<Integer> cumulativeScores) {
        cumulativeScoresOfPlayers.set(nth, cumulativeScores);
    }

    public List<Integer> nthCumulativeScoresOf(int nth) {
        return cumulativeScoresOfPlayers.get(nth);
    }
}
