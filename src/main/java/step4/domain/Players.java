package step4.domain;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;

public class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }


    public List<String> names() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());

    }

    public boolean isAllFinished() {
        return players.stream()
                .map(Player::getFrames)
                .allMatch(Frames::isFinished);
    }

    public List<GameHistory> filteredMap(Predicate<Player> filter, Function<Player, GameHistory> function) {
        return players.stream()
                .filter(filter)
                .map(function)
                .collect(Collectors.toList());
    }

    public void filteredForEach(Predicate<Player> filter, Consumer<Player> consumer) {
        players.stream()
                .filter(filter)
                .forEach(consumer);
    }

    public GameHistories createFramesHistories() {
        return players.stream()
                .map(player -> {
                    Frames frames = player.getFrames();
                    return GameHistory.Builder()
                            .playerName(player.getName())
                            .points(frames.getScores())
                            .marks(frames.getMarks())
                            .build();
                })
                .collect(collectingAndThen(Collectors.toList(), GameHistories::new));
    }
}
