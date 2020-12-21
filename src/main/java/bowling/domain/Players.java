package bowling.domain;

import bowling.dto.PlayerDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Players {
    public static final String CAN_NOT_FIND_PLAYER_ERR_MSG = "플레이어를 찾을 수 없습니다.";
    private final List<Player> value;

    private Players(PlayerNames playerNames) {
        value = new ArrayList<>();
        for (PlayerName playerName : playerNames) {
            Player player = Player.init(playerName);
            value.add(player);
        }
    }

    public static Players of(PlayerNames playerNames) {
        return new Players(playerNames);
    }

    public Player getCurrentPlayer(int currentFrameNo) {
        return value.stream()
                .filter(player -> !player.isEnd() && player.getCurrentFrameNo() == currentFrameNo)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(CAN_NOT_FIND_PLAYER_ERR_MSG));
    }

    public boolean isAllPlayerEndFrame(int currentFrameNo) {
        return value.stream()
                .allMatch(player -> player.getCurrentFrameNo() > currentFrameNo);
    }

    public boolean isAllPlayerEndGame() {
        return value.stream()
                .allMatch(Player::isEnd);
    }

    public List<PlayerDto> convertToDto() {
        return value.stream()
                .map(Player::convertToDto)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }
}
