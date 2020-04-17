package bowling;

import bowling.domain.bowlinggame.BowlingGames;
import bowling.domain.Player;

import java.util.List;
import java.util.stream.Collectors;

public class BowlingController {
    public static BowlingGames ready(List<String> inputPlayers) {
        List<Player> players = inputPlayers.stream()
                .map(Player::new)
                .collect(Collectors.toList());
        return BowlingGames.of(players);
    }
}
