package bowling.domain;

import bowling.annotations.ForUI;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BowlingGames {
    private final List<BowlingGame> values;

    public BowlingGames(List<String> names) {
        this.values = names.stream()
                .map(name -> new BowlingGame(new Player(name), new Frames()))
                .collect(Collectors.toList());
    }

    public boolean isEnd() {
        return values.stream()
                .allMatch(BowlingGame::isEnd);
    }

    @ForUI
    public List<BowlingGame> values() {
        return Collections.unmodifiableList(values);
    }
}
