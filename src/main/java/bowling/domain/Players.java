package bowling.domain;

import bowling.exception.BadSizeOfPlayersException;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class Players {
    private final List<Player> players = new LinkedList<>();

    public static Players of(int sizeOfPlayers, Supplier<Player> supplier) {
        Players playersObj = new Players();
        if (sizeOfPlayers < 1) {
            throw new BadSizeOfPlayersException("플레이어는 한명 이상이어야 합니다.");
        }
        for (int i = 0; i < sizeOfPlayers; i++) {
            playersObj.add(supplier.get());
        }
        return playersObj;
    }

    public void addToGame(Game game, Function<String, Roll> rollGenerator) {
        players.forEach(player -> game.addPlayer(
                player,
                rollGenerator));
    }

    private void add(Player player) {
        players.add(player);
    }
}
