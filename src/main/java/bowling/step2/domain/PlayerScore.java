package bowling.step2.domain;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

public class PlayerScore {
    private final Map<PlayerName, FrameScore> playerScore;

    private PlayerScore (Map<PlayerName, FrameScore> playerScore) {
        this.playerScore = playerScore;
    }

    public static PlayerScore of (Map<PlayerName, FrameScore> playerScore) {
        return new PlayerScore(playerScore);
    }

    public FrameScore getScoreOfPlayer (PlayerName playerName) {
       return playerScore.get(playerName);
    }

    public List<FrameScore> scores (Players players) {
        return players.stream()
                      .map(this::getScoreOfPlayer)
                      .collect(toList());
    }

    public PlayerScore initBy () {
        return playerScore
                .keySet()
                .stream()
                .collect(
                    collectingAndThen(
                        toMap(Function.identity(), v -> FrameScore.init()),
                        PlayerScore::of
                    )
                );
    }
}