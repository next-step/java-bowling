package bowling.controller;

import bowling.Games;
import bowling.domain.Game;
import java.util.List;
import java.util.stream.Collectors;

public class BowlingGameController {

    public Games createGames(List<String> names) {
        return new Games(names.stream()
            .map(Game::new)
            .collect(Collectors.toUnmodifiableList())
        );
    }

}
