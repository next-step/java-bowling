package bowling.domain;

import bowling.dto.GameDto;
import bowling.dto.PlayerDto;
import bowling.dto.PlayerStatusDto;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import static bowling.asset.Const.MAX_FRAME_NO;

public class Game {

    // NOTE: 넣은 순서대로 출력 및 테스트를 하기 위해 LinkedHashMap 자료구조를 이용한다.
    private final Map<Player, PlayerStatus> map = new LinkedHashMap<>();

    public void registerGamePrinter(Consumer<GameDto> consumer) {
        map.values()
                .forEach(status ->
                        status.register(subject ->
                                consumer.accept(exportGameDto())));
    }

    void addPlayer(Player player, Function<String, Roll> rollGenerator) {
        map.put(player, PlayerStatus.of(rollGenerator));
    }

    GameDto exportGameDto() {
        Map<PlayerDto, PlayerStatusDto> game = new LinkedHashMap<>();
        map.forEach((key, value) -> game.put(
                key.exportPlayerDto(),
                value.exportPlayerStatusDto()
        ));
        return new GameDto(game);
    }

    public void play() {
        for (int i = 0; i < MAX_FRAME_NO; i++) {
            playFrame();
        }
        playBonus();
    }

    private void playFrame() {
        play(PlayerStatus::playFrame);
    }

    private void playBonus() {
        play(PlayerStatus::playBonus);
    }

    private void play(Consumer<PlayerStatus> consumer) {
        map.values()
                .forEach(consumer);
    }
}
