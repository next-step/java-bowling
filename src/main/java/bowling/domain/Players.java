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

    public void play(Supplier<Roll> supplier) {
        while (!isGameOver()) {
            players.forEach(player -> play(supplier, player));
        }
        players.forEach(player -> bonusPlay(supplier, player));
    }

    private void play(Supplier<Roll> supplier, Player player) {
        player.play(supplier.get());
        if (!isStrike(player)) {
            player.play(supplier.get());
        }
    }

    private void bonusPlay(Supplier<Roll> supplier, Player player) {
        if (isBonus(player)) {
            player.play(supplier.get());
            player.play(Roll.of(0));
        }
    }

    private boolean isStrike(Player player) {
        return player.board()
                .isStrike();
    }

    private boolean isBonus(Player player) {
        return player.board()
                .isBonus();
    }

    private boolean isGameOver() {
        return players.stream()
                .map(Player::board)
                .map(Board::isGameOver)
                .reduce(true, Boolean::logicalAnd);
    }
}
