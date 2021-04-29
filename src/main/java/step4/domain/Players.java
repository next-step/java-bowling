package step4.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private final List<BowlingGame> bowlingGames;

    public Players(List<BowlingGame> bowlingGames) {
        this.bowlingGames = bowlingGames;
    }

    public List<Name> names() {
        return Collections.unmodifiableList(bowlingGames.stream()
                .map(BowlingGame::name)
                .collect(Collectors.toList()));
    }

    public List<Frames> frames() {
        return Collections.unmodifiableList(bowlingGames.stream()
                .map(BowlingGame::frames)
                .collect(Collectors.toList()));
    }

    public List<Scores> scores() {
        return Collections.unmodifiableList(bowlingGames.stream()
                .map(BowlingGame::scores)
                .collect(Collectors.toList()));
    }

    public List<BowlingGame> players() {
        return Collections.unmodifiableList(bowlingGames);
    }

    public boolean isAllFinished() {
        return bowlingGames.stream()
                .allMatch(BowlingGame::hasFinishedGame);
    }
}
