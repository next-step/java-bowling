package bowling.domain;

import bowling.domain.engine.frame.FrameNumber;
import bowling.dto.PlayersDto;

import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class Players {

    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public static Players of(List<String> playerNames) {
        List<Player> players = playerNames.stream()
                                          .map(Player::initialize)
                                          .collect(toList());

        return new Players(players);
    }

    public Player getFirstPlayerPlayingIn(FrameNumber frameNumber) {
        return players.stream()
                      .filter(player -> !player.isPlayedFrameOf(frameNumber))
                      .findFirst()
                      .orElseThrow(() -> new IllegalStateException("모든 플레이어가 해당 프레임에서 투구를 완료했습니다."));
    }

    public boolean isAllPlayersFinishedIn(FrameNumber frameNumber) {
        return players.stream()
                      .allMatch(player -> player.isPlayedFrameOf(frameNumber));
    }

    public PlayersDto export() {
        return players.stream()
                      .map(Player::export)
                      .collect(collectingAndThen(toList(), PlayersDto::new));
    }

}
