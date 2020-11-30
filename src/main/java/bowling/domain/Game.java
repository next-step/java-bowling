package bowling.domain;

import bowling.dto.PlayerDto;
import bowling.dto.PlayerStatusDto;
import bowling.dto.ScoreBoardDto;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.Map.Entry;

public class Game {
    private static final int MAX_FRAME_NO = 10;

    // NOTE: 넣은 순서대로 출력 및 테스트를 하기 위해 LinkedHashMap 자료구조를 이용한다.
    private final Map<Player, PlayerStatus> map = new LinkedHashMap<>();

    public void addPlayer(Player player, Function<Integer, Roll> rollGenerator) {
        map.put(player, PlayerStatus.of(rollGenerator));
    }

    public ScoreBoardDto exportScoreBoardDto() {
        Map<PlayerDto, PlayerStatusDto> scoreBoard = new LinkedHashMap<>();
        map.entrySet()
                .stream()
                .forEach(entry -> scoreBoard.put(
                        entry.getKey().exportPlayerDto(),
                        entry.getValue().exportPlayerStatusDto()
                ));
        return new ScoreBoardDto(scoreBoard);
    }

    public void play() {
        IntStream.range(0, MAX_FRAME_NO)
                .forEach(i -> playFrame());
        playBonus();
    }

    private void playFrame() {
        play(PlayerStatus::playFrame);
    }

    private void playBonus() {
        play(PlayerStatus::playBonus);
    }

    private void play(Consumer<PlayerStatus> consumer) {
        map.entrySet()
                .stream()
                .map(Entry::getValue)
                .forEach(consumer);
    }
}
