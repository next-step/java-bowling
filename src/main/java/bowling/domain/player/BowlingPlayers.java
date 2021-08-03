package bowling.domain.player;

import java.util.List;
import java.util.stream.Collectors;

public class BowlingPlayers {
    private static final int IDX_OF_FIRST_PLAYER = 0;

    private final List<BowlingPlayer> bowlingPlayers;

    public BowlingPlayers(List<String> names) {
        bowlingPlayers = names.stream()
                .map(BowlingPlayer::from)
                .collect(Collectors.toList());
    }

    public static BowlingPlayers init(List<String> names) {
        return new BowlingPlayers(names);
    }

    public BowlingPlayer firstPlayer() {
        return bowlingPlayers.get(IDX_OF_FIRST_PLAYER);
    }
}
