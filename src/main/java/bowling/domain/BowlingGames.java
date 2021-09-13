package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BowlingGames {

    private final List<BowlingGame> games = new ArrayList<>();

    public BowlingGames(String[] names) {
        for (String name : names) {
            games.add(new BowlingGame(name));
        }
    }

    public List<BowlingGame> games() {
        return Collections.unmodifiableList(games);
    }

    public boolean isNotEnd() {
        return games.stream()
                .allMatch(BowlingGame::isNotEnd);
    }
}
