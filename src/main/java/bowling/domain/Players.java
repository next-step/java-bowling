package bowling.domain;

import bowling.domain.frame.Frames;
import bowling.exception.DuplicatePlayerException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Players {

    private static final String DUPLICATE_EXCEPTION_MESSAGE = "동일한 이름의 플레이어는 추가할 수 없습니다";

    private final List<Player> players;
    private final List<Frames> framesList;

    private Players(List<Player> players) {
        this.players = players;
        validatePlayers();
        this.framesList = Stream.generate(Frames::init)
                .limit(players.size())
                .collect(Collectors.toList());
    }

    private void validatePlayers() {
        Set<Player> playersSet = new HashSet<>(players);
        if (playersSet.size() != players.size()) {
            throw new DuplicatePlayerException(DUPLICATE_EXCEPTION_MESSAGE);
        }
    }

    public static Players from(List<Player> players) {
        return new Players(players);
    }

    public int howManyPlayers() {
        return players.size();
    }
}
