package bowling;

import bowling.bowler.Bowler;
import bowling.frame.Frame;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static bowling.global.utils.ExceptionMessage.INVALID_BOWLER;

public class BowlingGames {

    private final List<BowlingGame> bowlingGames;

    private BowlingGames(List<BowlingGame> bowlingGames) {
        this.bowlingGames = bowlingGames;
    }

    public static BowlingGames start(List<Bowler> bowlers) {
        return new BowlingGames(bowlers.stream()
                .map(BowlingGame::create)
                .collect(Collectors.toList()));
    }

    public Frame bowl(Bowler bowler, String fellPins) {
        return getBowler(bowler).bowl(fellPins);
    }

    private BowlingGame getBowler(Bowler bowler) {
        return bowlingGames.stream()
                .filter(bowlingGame -> bowlingGame.equalsBowler(bowler))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_BOWLER));
    }

    public List<BowlingGame> getBowlingGames() {
        return Collections.unmodifiableList(bowlingGames);
    }

    public boolean isEnd() {
        return bowlingGames.stream()
                .allMatch(BowlingGame::isEnd);
    }

    public int size() {
        return bowlingGames.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BowlingGames that = (BowlingGames) o;
        return bowlingGames.equals(that.bowlingGames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bowlingGames);
    }
}
