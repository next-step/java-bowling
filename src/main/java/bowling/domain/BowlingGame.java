package bowling.domain;

import bowling.domain.player.PlayerName;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

public class BowlingGame {
    private final List<Lane> lanes;

    BowlingGame(List<Lane> lanes) {
        this.lanes = lanes;
    }

    public static BowlingGame of(List<String> playerNames) {
        validatePlayerNames(playerNames);

        return new BowlingGame(playerNames.stream()
                .map(name -> new Lane(new PlayerName(name)))
                .collect(toList()));
    }

    private static void validatePlayerNames(List<String> playerNames) {
        long distinctCount = playerNames.stream()
                .distinct()
                .count();
        if (playerNames.size() != distinctCount) {
            throw new IllegalArgumentException("플레이어 이름은 중복될 수 없습니다.");
        }
    }

    public boolean isAllFinished() {
        return lastLane().isFramesFinished();
    }

    private Lane lastLane() {
        return lanes.get(lanes.size() - 1);
    }

    public List<Lane> getLanes() {
        return Collections.unmodifiableList(lanes);
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
