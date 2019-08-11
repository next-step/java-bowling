package bowling.domain;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-20 03:24
 */
public class BowlingCenter {
    private final List<BowlingGame> games;

    public BowlingCenter(Players players) {
        this.games = players.getPlayers().stream()
                .map(player -> BowlingGame.of(player))
                .collect(Collectors.toList());
    }

    public boolean play(int fallCount) {
        if (isEndGamePlatform()) {
            return Boolean.FALSE;
        }
        return matchGamePlatform(minFrameNumber()).play(fallCount);
    }

    public List<String> displayState(Player player) {
        return games.stream()
                .filter(game -> game.matchPlayer(player))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .displayState();
    }

    public List<Integer> displayScore(Player player) {
        return games.stream()
                .filter(game -> game.matchPlayer(player))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .displayScore();
    }

    private boolean isEndGamePlatform() {
        return games.stream()
                .allMatch(game -> game.isGameOver());
    }

    private BowlingGame matchGamePlatform(int minFrameNumber) {
        return games.stream()
                .filter(game -> game.matchFrameNumber(minFrameNumber))
                .findFirst()
                .get();
    }

    private int minFrameNumber() {
        return games.stream()
                .mapToInt(BowlingGame::currentFrameNumber)
                .min()
                .orElseThrow(RuntimeException::new);
    }
}
