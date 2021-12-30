package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BowlingGames {
    private final List<BowlingGame> bowlingGames;

    private BowlingGames(List<BowlingGame> bowlingGames) {
        validate(bowlingGames);
        this.bowlingGames = new ArrayList<>(bowlingGames);
    }

    public static BowlingGames create(Players players) {
        return players.getPlayers()
                .stream()
                .map(BowlingGame::create)
                .collect(Collectors.collectingAndThen(Collectors.toList(), BowlingGames::new));
    }

    private void validate(List<BowlingGame> bowlingGames) {
        if (Objects.isNull(bowlingGames)) {
            throw new IllegalArgumentException("전달된 볼링게임들이 null 입니다.");
        }
        if (bowlingGames.isEmpty()) {
            throw new IllegalArgumentException("전달된 볼링게임들이 비어있습니다.");
        }
    }

    public List<BowlingGame> getBowlingGames() {
        return Collections.unmodifiableList(bowlingGames);
    }
}
