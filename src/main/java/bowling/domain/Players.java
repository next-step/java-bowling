package bowling.domain;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class Players {
    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players of(int size, Supplier<Player> supplier) {
        return IntStream.range(0, size)
                .mapToObj(i -> supplier.get())
                .collect(collectingAndThen(toList(), Players::new));
    }
}
