package bowling.domain;

import bowling.dto.PlayersDto;
import bowling.exception.BadSizeOfPlayersException;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

public class Players {
    private final List<Player> players = new LinkedList<>();

    public static Players of(int sizeOfPlayers, Supplier<Player> supplier) {
        if (sizeOfPlayers < 1) {
            throw new BadSizeOfPlayersException("플레이어는 한명 이상이어야 합니다.");
        }
        Players playersObj = new Players();
        for (int i = 0; i < sizeOfPlayers; i++) {
            playersObj.add(supplier.get());
        }
        return playersObj;
    }

    public void forEach(Consumer<Player> consumer) {
        players.forEach(consumer);
    }

    private void add(Player player) {
        players.add(player);
    }

    public PlayersDto exportPlayersDto() {
        return new PlayersDto(players
                .stream()
                .map(Player::exportPlayerDto)
                .collect(toList()));
    }
}
