package bowling.domain;

import bowling.dto.PlayerDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    public static final String CAN_NOT_FIND_PLAYER_ERR_MSG = "플레이어를 찾을 수 없습니다.";
    private final List<Player> value;

    private Players(List<Player> value) {
        this.value = value;
    }

    public static Players of(PlayerNames playerNames) {
        List<Player> value = new ArrayList<>();
        for (PlayerName playerName : playerNames) {
            Player player = Player.init(playerName);
            value.add(player);
        }
        return new Players(value);
    }

    public Player getCurrentPlayer(int currentFrameNo) {
        return value.stream()
                .filter(player -> !player.isEnd() && player.isFameNo(currentFrameNo))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(CAN_NOT_FIND_PLAYER_ERR_MSG));
    }

    public boolean isAllPlayerEndFrame(int currentFrameNo) {
        return value.stream()
                .allMatch(player -> player.isFrameEnd(currentFrameNo));
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
