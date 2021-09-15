package bowling.domain.player;

import bowling.domain.Results;
import bowling.domain.frame.AllFrames;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.score.TotalScoreBoard;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Players {
    private static final String PLAYER_NAMES_IS_NULL_EXCEPTION_STATEMENT = "플레이어의 이름 데이터가 널입니다";

    private final List<Player> players;
    private final TotalScoreBoard totalScoreBoard;

    private Players(List<String> playerNames, AllFrames allFrames) {
        validate(playerNames);
        players = playerNames.stream()
            .map(Player::from)
            .collect(Collectors.toList());

        totalScoreBoard = TotalScoreBoard.from(this, allFrames);
    }

    public static Players init(List<String> playerNames, AllFrames allFrames) {
        return new Players(playerNames, allFrames);
    }

    private void validate(List<String> playerNames) {
        if (Objects.isNull(playerNames)) {
            throw new IllegalArgumentException(PLAYER_NAMES_IS_NULL_EXCEPTION_STATEMENT);
        }
    }

    public List<String> names() {
        return players.stream()
            .map(Player::name)
            .collect(Collectors.toList());
    }

    public List<List<String>> results(AllFrames allFrames) {
        return IntStream.range(0, players.size())
            .mapToObj(i -> framesToResults(allFrames.nthFramesOf(i)))
            .collect(Collectors.toList());
    }

    public int numberOfPlayers() {
        return players.size();
    }

    public TotalScoreBoard totalScoreBoard() {
        return totalScoreBoard;
    }

    public Player nthOf(int nth) {
        return players.get(nth);
    }

    private List<String> framesToResults(Frames frames) {
        Results results = Results.from(frames);
        return results.results();
    }
}
