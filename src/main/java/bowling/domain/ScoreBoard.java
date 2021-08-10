package bowling.domain;

import bowling.domain.frame.Frames;
import bowling.domain.player.Player;
import bowling.domain.player.PlayerName;
import bowling.domain.score.TurnScore;
import bowling.exception.PlayerEmptyException;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static bowling.exception.PlayerEmptyException.*;

public final class ScoreBoard implements Iterable<Map.Entry<Player, Frames>> {
    public static final int DEFAULT_FRAMES_SIZE = 10;

    private final Map<Player, Frames> framesEachPlayer;

    private int playerCounter;

    private ScoreBoard(final Map<Player, Frames> framesEachPlayer) {
        this.framesEachPlayer = framesEachPlayer;
        playerCounter = 0;
    }

    public static ScoreBoard generate(final List<PlayerName> names) {
        validateGenerateNames(names);

        return IntStream.range(0, names.size())
                .boxed()
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toMap(
                                        index -> new Player(names.get(index), index),
                                        index -> new Frames()
                                ), ScoreBoard::new
                        )
                );
    }

    private static void validateGenerateNames(final List<PlayerName> names) {
        if (names.isEmpty()) {
            throw new PlayerEmptyException(ErrorCode.EMPTY);
        }
    }

    public void record(final Player player, final TurnScore score) {
        Frames frames = findFrames(player);

        frames.bowl(score);

        ++playerCounter;
    }

    public int currentFrameNumber(final Player player) {
        Frames frames = findFrames(player);

        return frames.currentFrameNumber();
    }

    public Player currentPlayer() {
        int playerIndex = playerIndex();

        //noinspection OptionalGetWithoutIsPresent
        return framesEachPlayer.keySet().stream()
                .filter(iPlayer -> iPlayer.matchesOrder(playerIndex))
                .findFirst().get();
    }

    private int playerIndex() {
        return playerCounter % framesEachPlayer.size();
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

    public int framesSize() {
        return framesEachPlayer.values()
                .iterator()
                .next()
                .size();
    }

    @Override
    public Iterator<Map.Entry<Player, Frames>> iterator() {
        return framesEachPlayer.entrySet().iterator();
    }
}
