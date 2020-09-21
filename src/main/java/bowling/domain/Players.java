package bowling.domain;

import java.util.List;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Players {

    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public Stream<Player> stream() {
        return players.stream();
    }

    public int size() { return players.size(); }

    public static class Builder {
        private final int countOfPlayer;
        private List<Player> players;

        public Builder(Supplier<Integer> nameSupplier) {
            this.countOfPlayer = nameSupplier.get();
        }

        public Builder withPlayerNames(IntFunction<String> nameFunction) {
            players = IntStream.rangeClosed(1, countOfPlayer)
                    .mapToObj(value -> Player.of(nameFunction.apply(value)))
                    .collect(Collectors.toList());
            return this;
        }

        public Players build() {
            return new Players(players);
        }
    }
}
