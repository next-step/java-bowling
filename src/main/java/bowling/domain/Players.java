package bowling.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Players {
    private final List<Player> players;
    private final ScoreBoard scoreBoard;

    public Players(List<String> playerNames, TotalFrames totalFrames) {
        players = playerNames.stream()
                             .map(name -> new Player(name))
                             .collect(Collectors.toList());

        scoreBoard = ScoreBoard.from(this, totalFrames);
    }

    public List<String> names() {
        return players.stream()
                      .map(Player::name)
                      .collect(Collectors.toList());
    }

    public List<List<String>> results(TotalFrames totalFrames) {
        return IntStream.range(0, players.size())
                        .mapToObj(i -> framesToResults(totalFrames.of(i)))
                        .collect(Collectors.toList());
    }

    public int numberOfPlayers() {
        return players.size();
    }

    public ScoreBoard scoreBoard() {
        return scoreBoard;
    }

    public Player of(int nth) {
        return players.get(nth);
    }

    private List<String> framesToResults(Frames frames) {
        return frames.results();
    }
}
