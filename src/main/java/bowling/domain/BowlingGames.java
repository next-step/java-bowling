package bowling.domain;

import java.util.List;
import java.util.stream.Collectors;

public class BowlingGames {
    private List<Bowling> bowlingGames;

    private BowlingGames(List<Bowling> bowlingGames) {
        this.bowlingGames = bowlingGames;
    }

    public static BowlingGames from(List<String> names) {
        return new BowlingGames(
                names.stream()
                        .map(Bowling::from)
                        .collect(Collectors.toList())
        );
    }

    public Bowling get(int index) {
        return bowlingGames.get(index);
    }

    public boolean matchLastIndex(int index) {
        return index == bowlingGames.size();
    }

    public boolean isEnd() {
        return bowlingGames.stream()
                .allMatch(Bowling::isEnd);
    }
}
