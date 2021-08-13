package bowling.domain.scoreboard;

import bowling.domain.frame.Frames;
import bowling.domain.player.Player;
import bowling.domain.player.PlayerGameIndex;
import bowling.domain.player.PlayerName;
import bowling.domain.score.TurnScore;
import bowling.exception.PlayerEmptyException;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static bowling.exception.PlayerEmptyException.ErrorCode;

public final class ScoreBoard implements Iterable<Map.Entry<Player, Frames>> {
    private final Map<Player, Frames> framesEachPlayer;
    private final PlayerOrderStrategy playerOrderStrategy;

    private ScoreBoard(final Map<Player, Frames> framesEachPlayer, final PlayerOrderStrategy playerOrderStrategy) {
        this.framesEachPlayer = framesEachPlayer;
        this.playerOrderStrategy = playerOrderStrategy;
    }

    public static ScoreBoard generate(final List<PlayerName> names,
                                      final Function<Map<Player,Frames>, PlayerOrderStrategy> ctorPlayerOrderStrategy) {
        validateGenerateNames(names);

        Map<Player, Frames> framesEachPlayer = IntStream.range(0, names.size())
                .boxed()
                .collect(
                        Collectors.toMap(
                                index -> new Player(names.get(index), new PlayerGameIndex(index)),
                                index -> new Frames()
                        )
                );
        return new ScoreBoard(framesEachPlayer, ctorPlayerOrderStrategy.apply(framesEachPlayer));
    }

    private static void validateGenerateNames(final List<PlayerName> names) {
        if (names.isEmpty()) {
            throw new PlayerEmptyException(ErrorCode.EMPTY);
        }
    }

    public void record(final Player player, final TurnScore score) {
        Frames frames = findFrames(player);

        frames.bowl(score);

        playerOrderStrategy.checkout();
    }

    public Player currentPlayer() {
        return playerOrderStrategy.currentPlayer();
    }

    private Frames findFrames(final Player player) {
        if (!framesEachPlayer.containsKey(player)) {
            throw new PlayerEmptyException(ErrorCode.NOT_FOUND);
        }
        return framesEachPlayer.get(player);
    }

    public boolean isCompleted() {
        return framesEachPlayer.values().stream()
                .allMatch(Frames::isCompleted);
    }

    @Override
    public Iterator<Map.Entry<Player, Frames>> iterator() {
        return framesEachPlayer
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .iterator();
    }
}
