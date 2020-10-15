package bowling.domain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BowlingGames {

    private final List<BowlingGame> bowlingGames;

    public BowlingGames(List<String> players) {
        this.bowlingGames = players.stream()
                .distinct()
                .map(BowlingGame::new)
                .collect(Collectors.toList());
    }

    public void pitch(Player player, int count) {
        findByPlayer(player).pitch(count);
    }

    public Frames getFrames(Player player) {
        return findByPlayer(player).getFrames();
    }

    public BowlingGame findByPlayer(Player player) {
        return bowlingGames.stream()
                .filter(it -> it.equalPlayer(player))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 player입니다."));
    }

    public List<BowlingGame> getBowlingGames() {
        return bowlingGames;
    }

    public boolean isEnd() {
        return this.bowlingGames.stream()
                .allMatch(BowlingGame::isEnd);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BowlingGames that = (BowlingGames) o;
        return Objects.equals(bowlingGames, that.bowlingGames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bowlingGames);
    }
}
