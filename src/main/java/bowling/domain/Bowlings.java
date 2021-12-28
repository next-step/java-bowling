package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class Bowlings {
    private final List<Bowling> values;
    private FrameIndex index;

    private Bowlings(List<Bowling> values) {
        this(values, FrameIndex.first());
    }

    private Bowlings(List<Bowling> values, FrameIndex index) {
        this.values = new ArrayList<>(values);
        this.index = index;
    }

    public static Bowlings create(List<Player> players) {
        return players.stream()
                .map(Bowling::create)
                .collect(collectingAndThen(toList(), Bowlings::new));
    }

    public List<Bowling> getValues() {
        return Collections.unmodifiableList(values);
    }

    public boolean hasNextPitching() {
        return values.stream()
                .anyMatch(Bowling::hasNext);
    }

    public FrameIndex getCurrentIndex() {
        return index;
    }

    public void increaseIndexIfFrameEnd() {
        boolean frameEnd = values.stream()
                .noneMatch(bowlingGame -> bowlingGame.isRunning(index));

        if (frameEnd) {
            index = index.next();
        }
    }
}
