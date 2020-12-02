package bowling.domain;

import bowling.dto.FrameNoDto;
import bowling.dto.GameDto;
import bowling.dto.PlayerDto;
import bowling.dto.PlayerStatusDto;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

import static bowling.asset.Const.MAX_FRAME_NO;

public class Game {
    // NOTE: 넣은 순서대로 출력 및 테스트를 하기 위해 LinkedHashMap 자료구조를 이용한다.
    private final Map<Player, PlayerStatus> map = new LinkedHashMap<>();
    private int frameNo = 1;

    public void addPlayer(Player player, Supplier<Roll> rollGenerator) {
        map.put(player, PlayerStatus.of(rollGenerator::get));
    }

    public void play() {
        Collection<PlayerStatus> statuses = map.values();
        while (frameNo <= MAX_FRAME_NO) {
            statuses.forEach(status -> status.play(frameNo));
            frameNo++;
        }
    }

    public void register(Runnable runnable) {
        map.values().forEach(status -> status.register(runnable));
    }

    public FrameNoDto exportFrameNoDto() {
        return new FrameNoDto(frameNo);
    }

    public GameDto exportGameDto() {
        Map<PlayerDto, PlayerStatusDto> game = new LinkedHashMap<>();
        map.forEach((player, status) -> game.put(
                player.exportPlayerDto(),
                status.exportPlayerStatusDto()
        ));
        return new GameDto(game);
    }
}
