package bowling.controller;

import bowling.domain.Player;
import bowling.domain.Roll;
import bowling.domain.play.PlayStatus;
import bowling.dto.FrameNoDto;
import bowling.dto.GameDto;
import bowling.dto.PlayerDto;
import bowling.dto.PlayerStatusDto;
import bowling.view.View;

import java.util.LinkedHashMap;
import java.util.Map;

import static bowling.asset.Const.MAX_FRAME_NO;

class Game {
    private final Map<Player, PlayStatus> map = new LinkedHashMap<>();
    private int frameNo = 1;

    void addPlayer(Player player) {
        map.put(player, new PlayStatus());
    }

    void play() {
        while (frameNo <= MAX_FRAME_NO) {
            map.forEach(this::play);
            frameNo++;
        }
    }

    private void play(Player player, PlayStatus status) {
        while (status.isContinue(frameNo)) {
            // Roll roll = getRoll(player.exportPlayerDto());
            Roll roll = getRoll(new FrameNoDto(frameNo));
            status.addRoll(roll);
            View.printGame(exportGameDto());
        }
    }

    private Roll getRoll(FrameNoDto frameNoDto) {
        return Roll.of(View.askRoll(frameNoDto)
                .getCountOfPins());
    }

    private Roll getRoll(PlayerDto playerDto) {
        return Roll.of(View.askRoll(playerDto)
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
