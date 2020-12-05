package bowling.controller;

import bowling.domain.Pin;
import bowling.domain.Player;
import bowling.domain.bowl.Bowl;
import bowling.dto.FrameNumberDto;
import bowling.dto.GameDto;
import bowling.dto.PlayerDto;
import bowling.dto.PlayerStatusDto;
import bowling.view.View;

import java.util.LinkedHashMap;
import java.util.Map;

import static bowling.asset.Const.MAX_FRAME_NO;

class Game {
    private final Map<Player, Bowl> map = new LinkedHashMap<>();
    private int frameNumber = 1;

    void addPlayer(Player player) {
        map.put(player, new Bowl());
    }

    void play() {
        while (frameNumber <= MAX_FRAME_NO) {
            map.forEach(this::play);
            frameNumber++;
        }
    }

    private void play(Player player, Bowl bowl) {
        while (bowl.isPlayable(frameNumber)) {
            bowl.addPin(
                    getRoll(new FrameNumberDto(frameNumber)) // getRoll(player.exportPlayerDto())
            );
            View.printGame(exportGameDto());
        }
    }

    private Pin getRoll(FrameNumberDto frameNumberDto) {
        return Pin.of(View.askRoll(frameNumberDto)
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
