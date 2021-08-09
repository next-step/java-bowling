package bowling.domain;

import bowling.domain.frame.Frames;
import bowling.domain.score.TurnScore;
import bowling.exception.PlayerEmptyException;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static bowling.exception.PlayerEmptyException.*;

public final class ScoreBoard implements Iterable<Map.Entry<Name, Frames>> {
    private static final int DEFAULT_FRAMES_SIZE = 10;
    private final Map<Name, Frames> framesEachName;

    private ScoreBoard(final Map<Name, Frames> framesEachName) {
        this.framesEachName = framesEachName;
    }

    public static ScoreBoard generate(final List<Name> names) {
        if (names.isEmpty()) {
            throw new PlayerEmptyException(ErrorCode.EMPTY);
        }

        return names.stream()
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toMap(
                                        iName -> iName, iName -> Frames.generate(DEFAULT_FRAMES_SIZE)
                                ), ScoreBoard::new
                        )
                );
    }

    public void bowl(final Name name, final TurnScore score) {
        Frames frames = findFrames(name)
                .orElseThrow(() -> new PlayerEmptyException(ErrorCode.NOT_FOUND));

        frames.bowl(score);
    }

    private Optional<Frames> findFrames(Name name) {
        if (framesEachName.containsKey(name)) {
            return Optional.of(framesEachName.get(name));
        }
        return Optional.empty();
    }

    public boolean isCompleted() {
        return framesEachName.values().stream()
                .allMatch(Frames::isCompleted);
    }

    public int size() {
        return this.framesEachName.size();
    }

    @Override
    public Iterator<Map.Entry<Name, Frames>> iterator() {
        return framesEachName.entrySet().iterator();
    }
}
