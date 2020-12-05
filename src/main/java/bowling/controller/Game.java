package bowling.controller;

import bowling.domain.Pin;
import bowling.domain.Player;
import bowling.domain.bowl.Bowl;
import bowling.dto.FrameNoDto;
import bowling.dto.GameDto;
import bowling.dto.PlayerDto;
import bowling.dto.PlayerStatusDto;
import bowling.view.View;

import java.util.LinkedHashMap;
import java.util.Map;

import static bowling.asset.Const.MAX_FRAME_NO;

class Game {
    private final Map<Player, Bowl> map = new LinkedHashMap<>();
    private int frameNo = 1;

    void addPlayer(Player player) {
        map.put(player, new Bowl());
    }

    void play() {
        while (frameNo <= MAX_FRAME_NO) {
            map.forEach(this::play);
            frameNo++;
        }
    }

    private void play(Player player, Bowl bowl) {
        while (bowl.isPlayable(frameNo)) {
            bowl.addPin(
                    getRoll(new FrameNoDto(frameNo)) // getRoll(player.exportPlayerDto())
            );
            View.printGame(exportGameDto());
        }
    }

    private Pin getRoll(FrameNoDto frameNoDto) {
        return Pin.of(View.askRoll(frameNoDto)
                .getCountOfPins());
    }

    private Pin getRoll(PlayerDto playerDto) {
        return Pin.of(View.askRoll(playerDto)
                .getCountOfPins());
    }

    private GameDto exportGameDto() {
        Map<PlayerDto, PlayerStatusDto> game = new LinkedHashMap<>();
        map.forEach((player, status) -> game.put(
                player.exportPlayerDto(),
                status.exportPlayerStatusDto()
        ));
        return new GameDto(game);
    }
}
