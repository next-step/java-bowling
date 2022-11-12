package bowling.domain;

import bowling.domain.player.PlayerName;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

public class BowlingGame {
    private final List<Lane> lanes;

    public BowlingGame(List<Lane> lanes) {
        this.lanes = lanes;
    }

    public static BowlingGame of(List<String> playerNames) {
        return new BowlingGame(playerNames.stream()
                .map(name -> new Lane(new PlayerName(name)))
                .collect(toList()));
    }

    public boolean isAllFinished() {
        return lastLane().isFramesFinished();
    }

    public List<Lane> getLanes() {
        return Collections.unmodifiableList(lanes);
    }

    private Lane lastLane() {
        return lanes.get(lanes.size() - 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BowlingGame)) return false;

        BowlingGame bowlingGame1 = (BowlingGame) o;

        return Objects.equals(lanes, bowlingGame1.lanes);
    }

    @Override
    public int hashCode() {
        return lanes != null ? lanes.hashCode() : 0;
    }
}
