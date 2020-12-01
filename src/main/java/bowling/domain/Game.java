package bowling.domain;

import bowling.dto.PlayerDto;
import bowling.dto.PlayerStatusDto;
import bowling.dto.ScoreBoardDto;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import static bowling.asset.Const.MAX_FRAME_NO;

public class Game {

    // NOTE: 넣은 순서대로 출력 및 테스트를 하기 위해 LinkedHashMap 자료구조를 이용한다.
    private final Map<Player, PlayerStatus> map = new LinkedHashMap<>();

    public void registerScoreBoardPrinter(Consumer<ScoreBoardDto> consumer) {
        map.values()
                .forEach(status ->
                        status.register(subject ->
                                consumer.accept(exportScoreBoardDto())));
    }

    void addPlayer(Player player, Function<Integer, Roll> rollGenerator) {
        map.put(player, PlayerStatus.of(rollGenerator));
    }

    ScoreBoardDto exportScoreBoardDto() {
        Map<PlayerDto, PlayerStatusDto> scoreBoard = new LinkedHashMap<>();
        map.forEach((key, value) -> scoreBoard.put(
                key.exportPlayerDto(),
                value.exportPlayerStatusDto()
        ));
        return new ScoreBoardDto(scoreBoard);
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
